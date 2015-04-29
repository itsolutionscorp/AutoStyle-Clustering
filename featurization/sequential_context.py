#!/usr/bin/env python
'''
Created on Mar 2, 2015

@author: jmoghadam
'''

import glob
import numpy as np
from syntax_tree.tree import control_flow_and_library_tree, Node, printTree

def has_sequential(ast, node_type):
    stack = []
    stack.append(ast)
    printTree(ast)
    while stack:
        current = stack.pop()
        children = Node.get_children(current)
        children_types = [Node.get_label(child) for child in children]
        if children_types.count(node_type) > 1:
            return True
        stack += children
    return False
        

def sequential_context_features(source):
    files = glob.glob(source + '/*_ast')
    files.sort()
    n = len(files)
    sequence_features = np.zeros((n, 2)) 
    for i, filename in enumerate(files):
        ast = control_flow_and_library_tree(filename, 'combine_anagrams')
        if has_sequential(ast, 'cond'):
            sequence_features[i, 0] = 1
        if has_sequential(ast, 'iter'):
            sequence_features[i, 1] = 1
    return sequence_features

def main():
    sequential_feature = sequential_context_features('./data/ast/')
    np.savetxt('./data/feature/sequential_context_features.np', sequential_feature)
    with open('data/feature/sequential_context_names.np', 'w') as f:
        f.write('sequential conditional' + '\n')
        f.write('sequential iteration' + '\n')
            
if __name__ == '__main__':
    main()
