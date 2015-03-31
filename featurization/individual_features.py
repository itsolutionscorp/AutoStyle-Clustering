#!/usr/bin/env python
'''
Created on Mar 30, 2015
Generate all the features corresponding to a particular submission.
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

def count_libcalls(index):
    '''Returns a list of library calls in this submission.
    '''
    ast = control_flow_and_library_tree(AST_FILES[index], 'combine_anagrams')
    calls = get_treegrams(ast, 1)
    stripped_calls = []
    for call in calls:
        stripped_calls.append(call.strip('()'))
    return stripped_calls

def flog_score(index):
    '''Returns the flog score of submission index 
    as a list of length 1.
    '''
    flog_output = terminal_ouput('flog', '-am', SOURCE_FILES[index])
    flog_score = flog_output[:flog_output.index(':')]
    return [float(flog_score), ]

def libcalls(index):
    '''Returns a list of 0s and 1s indicating
    whether submission index contains the corresponding
    library call.
    '''
    with open(ALL_LIBCALLS, 'r') as f:
        all_libcalls = f.readlines()
    n = len(all_libcalls)
    feature_vector = np.zeros((n, 1))
    libcall_names = count_libcalls(index)
    for libcall in libcall_names:
        if libcall in all_libcalls:
            i = all_libcalls.index(libcall)
            feature_vector[i, 0] = 1
    return feature_vector.flatten().tolist()
        

def handle_feature(index, feature):
    '''
    Returns the feature value of the submission with the given index.
    '''
    if feature == 'flog':
        return flog_score(index)
    elif feature == 'libcall':
        return libcalls(index)
    elif feature == 'data_structure':
        return []
    elif feature == 'only_context':
        return []
    elif feature == 'sequential_context':
        return []
    elif feature == 'duplicate_treegram':
        return []

def generate_features(index, features):
    '''
    Returns nx1 feature vector with all the features corresponding
    to the submission with index i.
    '''
    n = len(features)
    feature_vector = []
    for i, feature in enumerate(features):
        feature_values = handle_feature(index, feature)
        feature_vector += feature_values
    return np.reshape(np.array(feature_vector), (len(feature_vector), 1))

def main():
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('submission_index', type=int, help='Index of the submission.')
    parser.add_argument('output_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    submission_index = args.submission_index
    features = args.features
    output_file = args.output_file
    feature_vector = generate_features(args.submission_index, args.features)
    
    if not os.path.exists(output_file):
        all_features = feature_vector.T
    else:
        all_features = np.loadtxt(output_file)
        if len(all_features.shape) == 1:
            all_features = np.reshape(all_features, (1, all_features.shape[0]))
        all_features = np.append(all_features, feature_vector.T, 0)
    np.savetxt(output_file, all_features)

if __name__ == '__main__':
    main()
