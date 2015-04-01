#!/usr/bin/env python
'''
Created on Mar 30, 2015
Generates all the features corresponding to a particular submission.
In order to run this file, you must have:
    * A directory containing all the source files.
    * A directory containing the ast of each source file.
    * A list of all library calls.
@author: jmoghadam
'''
import argparse
import subprocess
import glob
import os.path
import numpy as np
from syntax_tree.tree import control_flow_and_library_tree, printTree, Node

HOME_DIR = './data/'
ALL_LIBCALLS = HOME_DIR + 'feature/all_libcalls.txt'
SOURCE_FILES = sorted(glob.glob(HOME_DIR + 'src/*.rb'))
AST_FILES = sorted(glob.glob(HOME_DIR + 'ast/*_ast'))

def terminal_ouput(*args):
    '''
    Calls the terminal command given by args, and
    returns its output as a string.
    '''
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

def count_libcalls(function_name, index):
    '''
    Returns a list of library calls in this submission.
    '''
    ast = control_flow_and_library_tree(AST_FILES[index], function_name)
    calls = get_treegrams(ast, 1)
    stripped_calls = []
    for call in calls:
        stripped_calls.append(call.strip('()'))
    return stripped_calls

def has_sequential(ast, node_type):
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
    if Node.get_label(ast) == node_type:
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
        if Node.get_label(current) == node_type:
            return True
        children = Node.get_children(current)
        stack += children
    return False
        

def sequential_context(index, function_name):
    '''
    Returns a list of size 1 indicating whether submission index
    contains sequential conditionals or iterations.
    '''
    filename = AST_FILES[index]
    ast = control_flow_and_library_tree(filename, function_name)
    feature_vector = [0 , 0]
    if has_sequential(ast, 'cond'):
        feature_vector[0] = 1
    if has_sequential(ast, 'iter'):
        feature_vector[1] = 1
    return feature_vector

def control_flow(function_name, index):
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
    filename = AST_FILES[index]
    ast = control_flow_and_library_tree(filename, function_name)
    if has_node(ast, 'cond'):
        feature_vector[0] = 1
    if has_nested(ast, 'cond'):
        feature_vector[1] = 1
    if has_sequential(ast, 'cond'):
        feature_vector[2] = 1
    if has_node(ast, 'iter'):
        feature_vector[3] = 1
    if has_nested(ast, 'iter'):
        feature_vector[4] = 1
    if has_sequential(ast, 'iter'):
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

def recursion(function_name, index):
    filename = AST_FILES[index]
    ast = control_flow_and_library_tree(filename, function_name)
    if uses_recursion(ast, function_name):
        return [1, ]
    else:
        return [0, ]

def contains_duplicates(l):
    return len(l) != len(set(l))

def duplicate_treegrams(index):
    '''
    Returns a list of length 1 indicating if submission
    index contains any duplicate treegrams of depth 3.
    '''
    filename = AST_FILES[index]
    ast = control_flow_and_library_tree(filename, 'combine_anagrams')
    treegrams = get_treegrams(ast, 3)
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

def libcalls(function_name, index):
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
    libcall_names = count_libcalls(function_name, index)
    for libcall in libcall_names:
        if libcall.strip() in all_libcalls:
            i = all_libcalls.index(libcall)
            feature_vector[i, 0] = 1
    return feature_vector.flatten().tolist()

def append_at_index(all_features, feature_vector, index):
    while (all_features.shape[0] <= index):
        all_features = np.append(all_features, np.zeros((1, all_features.shape[1])), 0)
    all_features[index, :] = feature_vector.T
    return all_features
        

def handle_feature(function_name, index, feature):
    '''
    Returns the feature value of the submission with the given index.
    '''
    if feature == 'flog':
        return flog_score(index)
    elif feature == 'libcall':
        return libcalls(function_name, index)
    elif feature == 'data_structure':
        return []
    elif feature == 'control_flow':
        return control_flow(function_name, index)
    elif feature == 'recursion':
        return recursion(function_name, index)
    elif feature == 'duplicate_treegram':
        return duplicate_treegrams(index)

def generate_individual_features(function_name, index, features):
    '''
    Returns nx1 feature vector with all the features corresponding
    to the submission with index i.
    '''
    feature_vector = []
    for feature in features:
        feature_values = handle_feature(function_name, index, feature)
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
    parser.add_argument('output_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    function_name = args.function_name
    submission_index = args.submission_index
    features = args.features
    output_file = args.output_file
    
    feature_vector = generate_individual_features(function_name, submission_index, features)
    
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
