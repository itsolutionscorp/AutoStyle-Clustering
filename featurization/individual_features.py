#!/usr/bin/env python
'''
Created on Mar 30, 2015
Generates all the features corresponding to a particular submission.
In order to run this file, you must have:
    * A directory containing all the source files.
    * A directory containing the ast of each source file (for ruby).
    * A list of all library calls.
@author: jmoghadam
'''
import argparse
import subprocess
import glob
import os
import os.path
import betterast
import ast
import math
import numpy as np
import sys
sys.path.insert(0, os.path.abspath('../syntax_tree'))
sys.path.insert(0, os.path.abspath('syntax_tree'))
from tree import control_flow_and_library_tree, printTree, Node

DUPLICATE_DEPTH = 4
HOME_DIR = './data/'
ALL_LIBCALLS = HOME_DIR + 'feature/all_libcalls.txt'
SOURCE_FILES = sorted(glob.glob(HOME_DIR + 'src/*'))
AST_FILES = sorted(glob.glob(HOME_DIR + 'ast/*_ast'))

def abc_score(language, function_name, index):
    '''
    Return the abc score of submission index.
    Abc score counts assignments, branches, and conditionals
    '''
    ast = generate_python_ast(SOURCE_FILES[index])
    assign, branch, cond, calls, cpts = 0, 0, 0, 0, 0
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        key = Node.get_label(current)
        if key in ("Assign", "AugAssign"):
            assign += 1
        elif key in ("Call", "In"):
            calls += 1
        elif key in ("Call", "While", "For", "Raise", "Break"):
            branch += 1
        elif key == function_name:
            cpts += 1
        stack += Node.get_children(current)[::-1]
    score = round(math.sqrt(branch ** 2 + assign ** 2 + cond ** 2 + .4 * (calls ** 2) + cpts ** 2), 2)
    return [score, ]
    

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
        if label == "FunctionDef":
            name = [r[1] for r in ast.iter_fields(kid) if r[0] == 'name'][0]
            label = "FunctionDef: " + name
        if i==0 and node.label == "Call" and 'func' in dir(ast_node) :
            label = [r[1] for r in ast.iter_fields(kid) if r[0] == 'attr' or r[0] == 'id'][0]
        if label in functions:
            label = functions[label]
        kidNode = betterast.Node(label)
        node.addkid(kidNode)
        build_tree(kidNode, kid, functions)
    return node

def generate_python_ast(filename):
    '''Generate an Node object representing an
    ast for a python file.
    '''
    try:
        ast_node = ast.parse(open(filename, 'r').read(), mode='exec')
    except Exception as e:
        print "FAILED: " + filename + "  error: " + str(e)
        return
    ast_node = ast.fix_missing_locations(ast_node)
    b_node = betterast.Node(get_node_label(ast_node))
    tree = build_tree(b_node,ast_node,{})
    return tree

def generate_ast(language, index, function_name):
    '''Get the ast for submission index.
    TODO: generate ruby ast on the fly.
    '''
    if language == 'ruby':
        ast = control_flow_and_library_tree(AST_FILES[index], function_name)
    elif language == 'python':
        ast = generate_python_ast(SOURCE_FILES[index])
    return ast    

def terminal_ouput(*args):
    '''
    Calls the terminal command given by args, and
    returns its output as a string.
    '''
    print args
    command = subprocess.Popen(args, stdout=subprocess.PIPE)
    out, err = command.communicate()
    return out

def treegram_string(node, depth):
    '''
    Returns a sexp string that is a treegram of the node down to
    the given depth.
    '''
    s = '('
    s += Node.get_label(node)
    if depth != 1:
        if not Node.get_children(node):
            return None
        else:
            for child in Node.get_children(node):
                t_string = treegram_string(child, depth - 1)
                if not t_string:
                    return None
                else:
                    s += ', ' + treegram_string(child, depth - 1)
    return s + ')'

def get_treegrams(ast, depth):
    '''
    Returns a list of all treegrams
    of the ast of size depth.
    '''
    treegrams = []
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        t_string = treegram_string(current, depth)
        if t_string:
            treegrams.append(t_string)
        stack += Node.get_children(current)[::-1]
    return treegrams

def count_libcalls(ast, function_name, index):
    '''
    Returns a list of library calls in this submission.
    '''
    
    calls = get_treegrams(ast, 1)
    stripped_calls = []
    for call in calls:
        stripped_calls.append(call.strip('()'))
    return stripped_calls

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
        children_types = [Node.get_label(child) for child in children]
        for node_type in node_types:
            if children_types.count(node_type) > 1:
                return True
        stack += children
    return False

def has_nested(ast, node_type):
    '''
    Returns True if the ast has nested nodes of the given
    node_type anywhere in the ast.
    '''
    children = Node.get_children(ast)
    if Node.get_label(ast) in node_type:
        for child in children:
            if has_node(child, node_type):
                return True
    else:
        for child in children:
            if has_nested(child, node_type):
                return True
    return False

def has_node(ast, node_type):
    '''
    Returns True if the ast has a node of the given
    node_type anywhere in the ast.
    ''' 
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        if Node.get_label(current) in node_type:
            return True
        children = Node.get_children(current)
        stack += children
    return False
        

def sequential_context(language, index, function_name):
    '''
    Returns a list of size 1 indicating whether submission index
    contains sequential conditionals or iterations.
    '''
    ast = generate_ast(language, index, function_name)
    feature_vector = [0 , 0]
    if has_sequential(ast, ['cond', 'If']):
        feature_vector[0] = 1
    if has_sequential(ast, ['iter', 'For']):
        feature_vector[1] = 1
    return feature_vector

def control_flow(language, function_name, index):
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
    ast = generate_ast(language, index, function_name)
    if has_node(ast, ['cond', 'If']):
        feature_vector[0] = 1
    if has_nested(ast, ['cond', 'If']):
        feature_vector[1] = 1
    if has_sequential(ast, ['cond', 'If']):
        feature_vector[2] = 1
    if has_node(ast, ['iter', 'For', 'While']):
        feature_vector[3] = 1
    if has_nested(ast, ['iter', 'For', 'While']):
        feature_vector[4] = 1
    if has_sequential(ast, ['iter', 'For', 'While']):
        feature_vector[5] = 1
    return feature_vector

def uses_recursion(ast, function_name):
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        if Node.get_label(current) == function_name:
            return True
        stack += Node.get_children(current)[::-1]
    return False

def recursion(language, function_name, index):
    '''Return whether submission index contains any calls to
    function_name (indicating recursion).
    '''
    ast = generate_ast(language, index, function_name)
    if uses_recursion(ast, function_name):
        return [1, ]
    else:
        return [0, ]

def contains_duplicates(l):
    '''
    Return whether the list contains duplicates.
    '''
    return len(l) != len(set(l))

def duplicate_treegrams(language, function_name, index):
    '''
    Returns a list of length 1 indicating if submission
    index contains any duplicate treegrams of depth 3.
    '''
    ast = generate_ast(language, index, function_name)
    treegrams = get_treegrams(ast, DUPLICATE_DEPTH)
    if contains_duplicates(treegrams):
        return [1, ]
    else:
        return [0, ]

def flog_score(index):
    '''
    Returns the flog score of submission index 
    as a list of length 1.
    '''
    flog_output = terminal_ouput('flog', '-am', SOURCE_FILES[index])
    flog_score = flog_output[:flog_output.index(':')]
    return [float(flog_score), ]

def libcalls(language, function_name, index):
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
    ast = generate_ast(language, index, function_name)
    libcall_names = count_libcalls(ast, function_name, index)
    for libcall in libcall_names:
        if libcall.strip() in all_libcalls:
            i = all_libcalls.index(libcall)
            feature_vector[i, 0] = 1
    return feature_vector.flatten().tolist()

def append_at_index(all_features, feature_vector, index):
    '''
    Place the feature vector into the matrix all_features, at row index.
    If there aren't enough rows, fill the rows before with zeroes.
    '''
    while (all_features.shape[0] <= index):
        all_features = np.append(all_features, np.zeros((1, all_features.shape[1])), 0)
    all_features[index, :] = feature_vector.T
    return all_features
        

def handle_feature(language, function_name, index, feature):
    '''
    Returns the feature value of the submission with the given index.
    '''
    if feature == 'abc':
        return abc_score(language, function_name, index)
    if feature == 'flog':
        return flog_score(index)
    elif feature == 'libcall':
        return libcalls(language, function_name, index)
    elif feature == 'data_structure':
        return []
    elif feature == 'control_flow':
        return control_flow(language, function_name, index)
    elif feature == 'recursion':
        return recursion(language, function_name, index)
    elif feature == 'duplicate_treegram':
        return duplicate_treegrams(language, function_name, index)

def generate_individual_features(language, function_name, index, features, home_dir):
    '''
    Returns nx1 feature vector with all the features corresponding
    to the submission with index i.
    '''
    global HOME_DIR
    global ALL_LIBCALLS
    global SOURCE_FILES
    global AST_FILES
    
    HOME_DIR = home_dir.rstrip("/") + "/"
    ALL_LIBCALLS = HOME_DIR + 'feature/all_libcalls.txt'
    SOURCE_FILES = sorted(glob.glob(HOME_DIR + 'src/*'))
    AST_FILES = sorted(glob.glob(HOME_DIR + 'ast/*_ast'))
    
    feature_vector = []
    for feature in features:
        feature_values = handle_feature(language, function_name, index, feature)
        feature_vector += feature_values
    return np.reshape(np.array(feature_vector), (len(feature_vector), 1))

def main():
    '''
    Parse command line arguments, pass them to generate_features,
    and append the given feature vector to an output file.
    '''
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('function_name', help='Name of the function to compute features about.')
    parser.add_argument('submission_index', type=int, help='Index of the submission.')
    parser.add_argument('language', help='ruby or python')
    parser.add_argument('output_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('home_directory', help = 'Path to data directory')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    function_name = args.function_name
    submission_index = args.submission_index
    features = args.features
    language = args.language
    home_dir = args.home_directory
    output_file = args.output_file

    feature_vector = generate_individual_features(language, function_name, submission_index, features, home_dir)
    
    if not os.path.exists(output_file):
        all_features = feature_vector.T
    else:
        all_features = np.loadtxt(output_file)
        if len(all_features.shape) == 1:
            all_features = np.reshape(all_features, (1, all_features.shape[0]))
        all_features = append_at_index(all_features, feature_vector, submission_index)
    np.savetxt(output_file, all_features)

if __name__ == '__main__':
    main()
