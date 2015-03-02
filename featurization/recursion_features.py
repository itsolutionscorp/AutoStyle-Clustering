#!/usr/bin/env python
'''
Created on Mar 2, 2015

@author: jmoghadam
'''
import glob
import numpy as np
from syntax_tree.tree import control_flow_and_library_tree, Node, printTree

def uses_recursion(ast, function_name):
    stack = []
    stack.append(ast)
    while stack:
        current = stack.pop()
        if Node.get_label(current) == function_name:
            return True
        stack += Node.get_children(current)[::-1]
    return False

def recursion_features(source):
    files = glob.glob(source + '/*_ast')
    files.sort()
    n = len(files)
    has_recursion = np.zeros((n, 1)) 
    for i, filename in enumerate(files):
        ast = control_flow_and_library_tree(filename, 'combine_anagrams')
        if uses_recursion(ast, 'combine_anagrams'):
            has_recursion[i, 0] = 1
    return has_recursion
            
def main():
    recursion_feature = recursion_features('./data/ast/')
    np.savetxt('./data/feature/recursion_features.np', recursion_feature)
    with open('data/feature/recursion_names.np', 'w') as f:
        f.write('recursion' + '\n')
            
if __name__ == '__main__':
    main()
