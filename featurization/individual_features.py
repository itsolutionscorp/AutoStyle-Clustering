#!/usr/bin/env python
'''
Created on Mar 30, 2015
Generates all the features corresponding to a particular submission.
In order to run this file, you must have:
    * A directory containing all the source files.
    * A list of all library calls.
@author: jmoghadam
'''
import argparse
import subprocess
import glob
import os.path
import betterast
import ast
import math
import numpy as np
import sys
import re
import sexpdata
sys.path.insert(0, os.path.abspath('../syntax_tree'))
sys.path.insert(0, os.path.abspath('syntax_tree'))
from tree import control_flow_and_library_tree, cfl_tree_from_string, printTree, Node

def natural_sort(l): 
    '''
    Sorts numbered filenames by their integer value 
    Ref: http://blog.codinghorror.com/sorting-for-humans-natural-sort-order/
    '''    
    convert = lambda text: int(text) if text.isdigit() else text.lower() 
    alphanum_key = lambda key: [ convert(c) for c in re.split('([0-9]+)', key) ] 
    return sorted(l, key = alphanum_key)

DUPLICATE_DEPTH = 4
LINE_DELIMITER = '::'
HOME_DIR = './data/'
ALL_LIBCALLS = HOME_DIR + 'feature/all_libcalls.txt'
SOURCE_FILES = natural_sort(glob.glob(HOME_DIR + 'src/*'))

def ast_from_sexp(s):
    '''
    Generate an ast from a simple s-expression string.
    The top node is assumed to be a function definition.
    '''
    def recursive_ast_from_sexp(parent, sub_sexp):
        if not sub_sexp:
            return
        elif sub_sexp.__class__.__name__ == 'list':
            level_node = sub_sexp.pop(0)
            level_label = 'NOT SYMBOL'
            if level_node.__class__.__name__ == "Symbol":
                level_label = level_node.value()
                if Node.get_label(parent) == 'root':
                    level_label = 'FunctionDef: ' + level_label
            new_node = Node(level_label)
            parent.addkid(new_node)
            for e in sub_sexp:
                recursive_ast_from_sexp(new_node, e)
    
    try:
        exp = sexpdata.loads(s)
        root = Node('root')
        recursive_ast_from_sexp(root, exp)
        return root
    except Exception:
        print s    

def abc_score(ast):
    '''
    Return a simple abc score of submission index.
    Abc score counts assignments, branches, and conditionals.
    '''
    assign, branch, cond, calls, cpts = 0, 0, 0, 0, 0
    cpts = loops_and_recursion(ast)
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        key = Node.get_label(current).split(":")[0]
        if key in ("Assign", "AugAssign"):
            assign += 1
        elif key in ("Call", "In"):
            calls += 1
        elif key in ("If", "While", "For", "Raise", "Break", "cond", "iter"):
            branch += 1
        elif key in ["Compare","Try", "ExceptHandler"]:
            cond+=1
        stack += Node.get_children(current)[::-1]
    score = round(math.sqrt(branch ** 2 + assign ** 2 + cond ** 2 + .4 * (calls ** 2) + cpts ** 2), 2)
    return [score, ], [0, ]

def loops_and_recursion(tree):
    '''
    @type tree: node
    @param tree: the root node of an AST
    returns a score based on the number of loops and recursive calls in the AST
    '''
    queue = [tree]
    func_defs = []
    recursions = set()
    loop_total = 0
    loop_tracker = []
    loop_count = 0
    while len(queue) > 0:
        temp_node = queue.pop(0)
        label = Node.get_label(temp_node).replace("<class '_ast.","").replace("'>","").split(":")[0]
        if temp_node in loop_tracker:
            loop_tracker.remove(temp_node)
            if len(loop_tracker) == 0:
                loop_total += loop_count ** 2
                loop_count = 0
        if label[0:11] == 'FunctionDef':
            func_defs.append(label[12:]) 
        elif label in func_defs:
            recursions.add("Recursion on " +label)
        elif label in ["For", "While", "iter"]:
            if len(queue) > 0:
                loop_tracker.append(queue[0])
            loop_count+=1
        queue = temp_node.children + queue
    if loop_count > 0:
        loop_total += loop_total **2
    return loop_total + len(recursions)    

def get_node_label(node):
    '''
    Get the label of a python ast object.
    '''
    return str(type(node)).replace("<class '_ast.","").replace("'>","")

def build_tree(node, ast_node, functions):
    '''
    Recursively construct a Node object out of a python ast object.
    '''
    if isinstance(ast_node, str) or isinstance(ast_node, int) or (ast_node == None):
        return node
    n = [i for i in ast.iter_child_nodes(ast_node)]
    for i,kid in enumerate(n):
        label = get_node_label(kid)
        if 'lineno' in kid._attributes:
            line = kid.lineno
        elif 'lineno' in ast_node._attributes:
            line = ast_node.lineno
        else:
            line = 0
        if label == "FunctionDef":
            name = [r[1] for r in ast.iter_fields(kid) if r[0] == 'name'][0]
            label = "FunctionDef: " + name
        if i==0 and node.label[0:4] == "Call" and 'func' in dir(ast_node):
            label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
        if label in functions:
            label = functions[label]
        kidNode = betterast.Node(label + LINE_DELIMITER + str(line))
        node.addkid(kidNode)
        build_tree(kidNode, kid, functions)
    return node

def build_structure_tree(node, ast_node, functions):
    '''
    Recursively construct a Node object out of a python ast object.
    Only build nodes for loops and conditionals.
    '''
    if isinstance(ast_node, str) or isinstance(ast_node, int) or (ast_node == None):
        return node
    n = [i for i in ast.iter_child_nodes(ast_node)]
    for i, kid in enumerate(n):
        label = get_node_label(kid)
        if 'lineno' in kid._attributes:
            line = kid.lineno
        elif 'lineno' in ast_node._attributes:
            line = ast_node.lineno
        else:
            line = 0
        if label == "FunctionDef":
            name = [r[1] for r in ast.iter_fields(kid) if r[0] == 'name'][0]
            label = "FunctionDef: " + name
        if i == 0 and node.label[0:4] == "Call" and 'func' in dir(ast_node):
            label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
        if label in functions:
            label = functions[label]
        if label in ('For', 'If', 'While'):
            kidNode = betterast.Node(label)
            node.addkid(kidNode)
            build_structure_tree(kidNode, kid, functions)
        else:
            build_structure_tree(node, kid, functions)
    return node

def generate_python_ast(filename):
    '''
    Generate an Node object representing an ast for a python file.
    '''
    try:
        ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
    except Exception as e:
        print "FAILED: " + filename + "  error: " + str(e)
        return
    ast_node = ast.fix_missing_locations(ast_node)
    if 'lineno' in ast_node._attributes:
        b_node = betterast.Node(get_node_label(ast_node) + LINE_DELIMITER + ast_node.lineno)
    else:
        b_node = betterast.Node(get_node_label(ast_node))
    tree = build_tree(b_node,ast_node,{})
    return tree

def generate_python_structure_ast(filename):
    '''
    Generate an Node object representing an ast for a python file,
    but only consider nodes that are loops or conditionals.
    '''
    try:
        ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
    except Exception as e:
        print "FAILED: " + filename + "  error: " + str(e)
        return
    ast_node = ast.fix_missing_locations(ast_node)
    if 'lineno' in ast_node._attributes:
        b_node = betterast.Node(get_node_label(ast_node) + LINE_DELIMITER + ast_node.lineno)
    else:
        b_node = betterast.Node(get_node_label(ast_node))
    tree = build_structure_tree(b_node, ast_node, {})
    return tree

def generate_java_ast(filename, function_name, class_name):
    '''
    Generate a Node object representing an ast for
    a java method.
    '''
    current_dir = os.getcwd()
    filename = filename.replace("/src/", "/annotated_ast/")
    filepath = current_dir + "/" + filename + "/" + class_name + ".java.ast"
    # First check if the ast has already been stored. If not, recalculate it. 
    if os.path.exists(filepath):
        with open(filepath) as a:
            ast_string = a.read()
        return ast_from_sexp(ast_string)
    else:
        cp = "./:" + current_dir + "/scripts_java/lib/*:" + current_dir + "/scripts_java/JavaAST"
        ast_string = terminal_output("java", "-cp", cp, "ASTBuilder", filename + "/" + class_name + ".java", function_name, "-l")
        return ast_from_sexp(ast_string)

def generate_ruby_ast(filename, function_name):
    '''
    Return a Node object representing the ast of ruby code.
    '''
    ast_string = terminal_output('ruby', 'syntax_tree/ast_with_lines.rb', filename, function_name)
    return ast_from_sexp(ast_string)

def generate_ruby_structure_ast(filename, function_name):
    '''
    Return a Node object representing the ast of ruby code.
    The ast ignores all nodes that aren't loops or conditionals.
    '''
    ast_string = terminal_output('ruby', 'syntax_tree/ast_with_lines.rb', filename, function_name, '-s')
    return ast_from_sexp(ast_string)
    

def generate_ast(language, index, function_name, class_name):
    '''
    Get the ast for submission index.
    The ast is an object of type Node.
    '''
    if language == 'ruby':
        ast = generate_ruby_ast(SOURCE_FILES[index], function_name)
    elif language == 'python':
        ast = generate_python_ast(SOURCE_FILES[index])
    elif language == 'java':
        ast = generate_java_ast(SOURCE_FILES[index], function_name, class_name)
    return ast

def skeleton_from_ast(language, function_name, structure_ast, indentation):
    '''
    Return a string representing a code skeleton giving an ast containing only
    loops and conditionals.
    '''
    code_skeleton = ''
    if language == 'python':
        if not structure_ast:
            return ''
        code_skeleton += indentation
        if Node.get_label(structure_ast) == 'Module':
            code_skeleton = 'def ' + function_name + '(...):' + '\n'
        elif Node.get_label(structure_ast) == 'For':
            code_skeleton = 'for ... :' + '\n'
        elif Node.get_label(structure_ast) == 'While':
            code_skeleton = 'while ... :' + '\n'
        elif Node.get_label(structure_ast) == 'If':
            code_skeleton = 'if ... :' + '\n'
        for child in Node.get_children(structure_ast):
            code_skeleton += indentation + '    ' + skeleton_from_ast(language, function_name, child, indentation + '    ')
    return code_skeleton

def skeleton_from_source(language, source_file):
    '''
    Takes a piece of source code and generate a skeleton version of that 
    source code which contains only function definitions, loops, conditionals,
    and returns.
    
    TODO: make this more generic, clearly.
    '''
    skeleton = ''
    if language == 'ruby':
        with open(source_file, 'r') as f:
            for line in f:
                stripped_line = line.strip()
                if stripped_line.startswith('def '):
                    skeleton += line + '\n'
                elif stripped_line.startswith('while '):
                    skeleton += line[:line.index('while ')] + 'while ...' + '\n\n'
                elif stripped_line.startswith('until '):
                    skeleton += line[:line.index('until ')] + 'until ...' + '\n\n'
                elif stripped_line.startswith('for '):
                    skeleton += line[:line.index('for ')] + 'for ... in ...' + '\n\n'
                elif stripped_line.startswith('if '):
                    skeleton += line[:line.index('if ')] + 'if ...' + '\n\n'
                elif stripped_line.startswith('elsif '):
                    skeleton += line[:line.index('elsif ')] + 'elsif ...' + '\n\n'
                elif stripped_line.startswith('else'):
                    skeleton += line[:line.index('else')] + 'else' + '\n\n'
                elif stripped_line.startswith('unless '):
                    skeleton += line[:line.index('unless ')] + 'unless ...' + '\n\n'
                elif stripped_line.startswith('end'):
                    skeleton += line[:line.index('end')] + 'end' + '\n\n'
                elif stripped_line.startswith('return'):
                    skeleton += line[:line.index('return')] + 'return ...' + '\n\n'
                elif ' do' in stripped_line:
                    skeleton += line[:len(line) - len(line.lstrip())] + '... do |...|' + '\n\n'
    if language == 'python':
        with open(source_file, 'r') as f:
            for line in f:
                stripped_line = line.strip()
                if stripped_line.startswith('def '):
                    skeleton += line + '\n'
                elif stripped_line.startswith('for '):
                    skeleton += line[:line.index('for ')] + 'for ... :' + '\n\n'
                elif stripped_line.startswith('while '):
                    skeleton += line[:line.index('while ')] + 'while ... :' + '\n\n'
                elif stripped_line.startswith('if '):
                    skeleton += line[:line.index('if ')] + 'if ... :' + '\n\n'
                elif stripped_line.startswith('elif '):
                    skeleton += line[:line.index('elif ')] + 'elif ... :' + '\n\n'
                elif stripped_line.startswith('else:'):
                    skeleton += line[:line.index('else:')] + 'else:' + '\n\n'
                elif stripped_line.startswith('return'):
                    skeleton += line[:line.index('return')] + 'return ...' + '\n\n'
    return skeleton

def generic_skeleton_from_source(language, source_file):
    '''
    Takes a piece of source code and generate a skeleton version of that 
    source code which contains only function definitions, loops, conditionals,
    and returns.
    
    TODO: make this more generic, clearly.
    '''
    skeleton = ''
    if language == 'ruby':
        with open(source_file, 'r') as f:
            for line in f:
                stripped_line = line.strip()
                if stripped_line.startswith('def '):
                    skeleton += line + '\n'
                elif stripped_line.startswith('while '):
                    skeleton += 'iter ...' + '\n\n'
                elif stripped_line.startswith('until '):
                    skeleton += 'iter ...' + '\n\n'
                elif stripped_line.startswith('for '):
                    skeleton += 'iter ...' + '\n\n'
                elif stripped_line.startswith('if '):
                    skeleton += 'cond ...' + '\n\n'
                elif stripped_line.startswith('elsif '):
                    skeleton += 'cond ...' + '\n\n'
                elif stripped_line.startswith('else'):
                    skeleton += 'cond ...' + '\n\n'
                elif stripped_line.startswith('unless '):
                    skeleton += 'cond ...' + '\n\n'
                elif stripped_line.startswith('end'):
                    skeleton += 'end' + '\n\n'
                elif ' do' in stripped_line:
                    skeleton += 'iter ...' + '\n\n'
                
    if language == 'python':
        with open(source_file, 'r') as f:
            for line in f:
                stripped_line = line.strip()
                if stripped_line.startswith('def '):
                    skeleton += line + '\n'
                elif stripped_line.startswith('for '):
                    skeleton += line[:line.indexo('for ')] + 'iter ... :' + '\n\n'
                elif stripped_line.startswith('while '):
                    skeleton += line[:line.index('while ')] + 'iter ... :' + '\n\n'
                elif stripped_line.startswith('if '):
                    skeleton += line[:line.index('if ')] + 'cond ... :' + '\n\n'
                elif stripped_line.startswith('elif '):
                    skeleton += line[:line.index('elif ')] + 'cond ... :' + '\n\n'
                elif stripped_line.startswith('else:'):
                    skeleton += line[:line.index('else:')] + 'cond ... :' + '\n\n'
                elif stripped_line.startswith('return'):
                    skeleton += line[:line.index('return')] + 'return ...' + '\n\n'
    return skeleton

def terminal_output(*args):
    '''
    Calls the terminal command given by args, and
    returns its output as a string.
    '''
    command = subprocess.Popen(args, stdout=subprocess.PIPE)
    out, err = command.communicate()
    return out

def get_label_and_line(node):
    '''
    Most nodes consist of both a name, and a line number that the thing
    in the node was supposed to occur at in the source code.
    
    Returns the name and the line number separate into a tuple.
    '''
    if LINE_DELIMITER in Node.get_label(node):
        label, line = Node.get_label(node).split(LINE_DELIMITER)
        return label, int(line)
    else:
        return (Node.get_label(node), 0)

def treegram_string(node, depth):
    '''
    Returns a sexp string that is a treegram of the node down to
    the given depth.
    '''
    s = '('
    label, line = get_label_and_line(node)
    s += label
    if depth != 1:
        if not Node.get_children(node):
            return None, 0
        else:
            for child in Node.get_children(node):
                t_string, t_line = treegram_string(child, depth - 1)
                if not t_string:
                    return None, 0
                else:
                    s += ', ' + treegram_string(child, depth - 1)[0]
    return s + ')', line

def get_treegrams(ast, depth):
    '''
    Returns a list of all treegrams of the ast of size depth.
    Also return the line numbers that the treegrams start on.
    '''
    treegrams = []
    lines = []
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        t_string, t_line = treegram_string(current, depth)
        if t_string:
            treegrams.append(t_string)
            lines.append(t_line)
        stack += Node.get_children(current)[::-1]
    return treegrams, lines

def count_libcalls(ast, function_name, index):
    '''
    Returns a list of library calls in this submission.
    '''
    
    calls, lines = get_treegrams(ast, 1)
    stripped_calls = []
    for call in calls:
        stripped_calls.append(call.strip('()'))
    return (stripped_calls, lines)

def has_sequential(ast, node_types):
    '''
    Returns True if the ast has sister nodes of the given
    node_type anywhere in the ast.
    ''' 
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        children = Node.get_children(current)
        children_types = [get_label_and_line(child)[0] for child in children]
        children_lines = [get_label_and_line(child)[1] for child in children]
        for node_type in node_types:
            if children_types.count(node_type) > 1:
                return True, children_lines[children_types.index(node_type)]
        stack += children
    return False, 0

def has_nested(ast, node_type):
    '''
    Returns True if the ast has nested nodes of the given
    node_type anywhere in the ast.
    '''
    children = Node.get_children(ast)
    label, line = get_label_and_line(ast)
    if label in node_type:
        for child in children:
            if has_node(child, node_type):
                return True, line
    else:
        for child in children:
            has, line = has_nested(child, node_type)
            if has:
                return True, line
    return False, 0

def has_node(ast, node_type):
    '''
    Returns True if the ast has a node of the given
    node_type anywhere in the ast.
    ''' 
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        label, line = get_label_and_line(current)
        if label in node_type:
            return True, line
        children = Node.get_children(current)
        stack += children
    return False, 0

def control_flow(ast):
    '''
    Returns a list indicating if submission index:
    * has a conditional
    * has a nested conditional
    * has sequential conditionals
    * has iteration
    * has nested iteration
    * has sequential iterations
    '''
    feature_vector = [0, 0, 0, 0, 0, 0]
    lines = [0, 0, 0, 0, 0, 0]
    
    has, line = has_node(ast, ['cond', 'If'])
    if has:
        feature_vector[0] = 1
        lines[0] = line
    has, line = has_nested(ast, ['cond', 'If'])
    if has:
        feature_vector[1] = 1
        lines[1] = line
    has, line = has_sequential(ast, ['cond', 'If'])
    if has:
        feature_vector[2] = 1
        lines[2] = line
    has, line = has_node(ast, ['iter', 'For', 'While'])
    if has:
        feature_vector[3] = 1
        lines[3] = line
    has, line = has_nested(ast, ['iter', 'For', 'While'])
    if has:
        feature_vector[4] = 1
        lines[4] = line
    has, line = has_sequential(ast, ['iter', 'For', 'While'])
    if has:
        feature_vector[5] = 1
        lines[5] = line
    return feature_vector, lines

def uses_recursion(ast, function_name):
    '''
    Return true if the ast contains a node with the given function name.
    '''
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        label, line = get_label_and_line(current)
        if label == function_name:
            return True, line
        stack += Node.get_children(current)[::-1]
    return False, 0

def recursion(ast, function_name):
    '''Return whether submission index contains any calls to
    function_name (indicating recursion).
    '''
    uses, line = uses_recursion(ast, function_name)
    if uses:
        return [1, ], [line, ]
    else:
        return [0, ], [0, ]

def contains_duplicate_treegrams(treegrams, lines):
    '''
    Return whether the list contains duplicates.
    Also return the line number associated with the duplicates.
    '''
    seen_treegrams = {}
    for treegram, line in zip(treegrams, lines):
        if treegram in seen_treegrams:
            return True, seen_treegrams[treegram]
        else:
            seen_treegrams[treegram] = line
    return False, 0

def duplicate_treegrams(ast):
    '''
    Returns a list of length 1 indicating if submission
    index contains any duplicate treegrams of depth 3.
    '''
    treegrams, lines = get_treegrams(ast, DUPLICATE_DEPTH)
    contains, line = contains_duplicate_treegrams(treegrams, lines)
    if contains:
        return [1, ], [line, ]
    else:
        return [0, ], [0, ]

def flog_score(index):
    '''
    Returns the flog score of submission index 
    as a list of length 1.
    '''
    flog_output = terminal_output('flog', '-am', SOURCE_FILES[index])
    flog_score = flog_output[:flog_output.index(':')]
    return [float(flog_score), ], [0, ]

def libcalls(ast, language, function_name, index, class_name):
    '''
    Returns a list of 0s and 1s indicating
    whether submission index contains the corresponding
    library call.
    '''
    all_libcalls = []
    with open(ALL_LIBCALLS, 'r') as f:
        for line in f:
            all_libcalls.append(line.strip())
    n = len(all_libcalls)
    feature_vector = np.zeros((n, 1))
    feature_lines = np.zeros((n, 1))
    libcall_names, libcall_lines = count_libcalls(ast, function_name, index)
    for libcall, line in zip(libcall_names, libcall_lines):
        if libcall.strip() in all_libcalls:
            i = all_libcalls.index(libcall)
            feature_vector[i, 0] = 1
            feature_lines[i, 0] = line
    return feature_vector.flatten().tolist(), feature_lines.flatten().tolist()

def append_at_index(all_features, feature_vector, index):
    '''
    Place the feature vector into the matrix all_features, at row index.
    If there aren't enough rows, fill the rows before with zeroes.
    '''
    while (all_features.shape[0] <= index):
        all_features = np.append(all_features, np.zeros((1, all_features.shape[1])), 0)
    all_features[index, :] = feature_vector.T
    return all_features

def list_to_2d_np(l):
    '''
    Return an nx1 numpy array of the items in the list l.
    '''
    return np.reshape(np.array(l), (len(l), 1))

def save_matrix(feature_vector, output_feature_file, submission_index):
    '''
    Save the numpy array feature_vector into the matrix stored in output_feature_file,
    or create that file if it doesn't exist.
    It will appear at row submission_index of the matrix.
    '''
    if not os.path.exists(output_feature_file):
        all_features = feature_vector.T
    else:
        all_features = np.loadtxt(output_feature_file)
        if len(all_features.shape) == 0:
            all_features = np.reshape(all_features, (1, 1))
        if len(all_features.shape) == 1:
            all_features = np.reshape(all_features, (1, all_features.shape[0]))
        all_features = append_at_index(all_features, feature_vector, submission_index)
    np.savetxt(output_feature_file, all_features)
        

def handle_feature(ast, language, function_name, index, feature, class_name):
    '''
    Returns the feature value of the submission with the given index.
    '''
    if (ast is None):
        raise Exception
    if feature == 'abc':
        return abc_score(ast)
    if feature == 'flog':
        return flog_score(index)
    elif feature == 'libcall':
        return libcalls(ast, language, function_name, index, class_name)
    elif feature == 'data_structure':
        return []
    elif feature == 'control_flow':
        return control_flow(ast)
    elif feature == 'recursion':
        return recursion(ast, function_name)
    elif feature == 'duplicate_treegram':
        return duplicate_treegrams(ast)

def generate_individual_features(language, function_name, index, features, home_dir, class_name):
    '''
    Returns nx1 feature vector with all the features corresponding
    to the submission with index i.
    '''
    global HOME_DIR
    global ALL_LIBCALLS
    global SOURCE_FILES
    
    HOME_DIR = home_dir.rstrip("/") + "/"
    ALL_LIBCALLS = HOME_DIR + 'feature/all_libcalls.txt'
    SOURCE_FILES = natural_sort(glob.glob(HOME_DIR + 'src/*'))
    
    ast = generate_ast(language, index, function_name, class_name)
    printTree(ast)
    
    feature_vector = []
    feature_lines = []
    for feature in features:
        feature_values, feature_values_lines = handle_feature(ast, language, function_name, index, feature, class_name)
        feature_vector += feature_values
        feature_lines += feature_values_lines
    return list_to_2d_np(feature_vector), list_to_2d_np(feature_lines)

def main():
    '''
    Parse command line arguments, pass them to generate_features,
    and append the given feature vector to an output file.
    '''
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('function_name', help='Name of the function to compute features about.')
    parser.add_argument('submission_index', type=int, help='Index of the submission.')
    parser.add_argument('language', help='ruby, python, or java')
    parser.add_argument('home_directory', help='Path to data directory')
    parser.add_argument('output_feature_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('-l', '--output_line_file', nargs='?', help='File to append the generated horizontal feature line vector to.')
    parser.add_argument('-c', '--class_name', help='Class name (required for java)')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    function_name = args.function_name
    submission_index = args.submission_index
    features = args.features
    language = args.language
    home_dir = args.home_directory
    class_name = args.class_name
    output_feature_file = args.output_feature_file
    output_line_file = args.output_line_file

    try:
        feature_vector, feature_lines = generate_individual_features(language, function_name, submission_index, features, home_dir, class_name)
    except Exception:
        return
    
    save_matrix(feature_vector, output_feature_file, submission_index)
    if output_line_file:
        save_matrix(feature_lines, output_line_file, submission_index)

if __name__ == '__main__':
    main()
