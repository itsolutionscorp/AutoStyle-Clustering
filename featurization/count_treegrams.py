#!/usr/bin/env python
'''
Created on Sep 19, 2014

@author: joe
'''

import argparse
import numpy as np
import glob
from syntax_tree.tree import control_flow_and_library_tree, printTree, Node
from collections import OrderedDict

def count_treegrams(source, depth, make_indicator):
    treegram_counts = OrderedDict()
    treegram_features = []
    treegram_names = []
    files = glob.glob(source + '/*_ast')
    files.sort()
    for i, filename in enumerate(files):
        treegram_features.append(OrderedDict())
        ast = control_flow_and_library_tree(filename, 'combine_anagrams')
        treegrams = get_treegrams(ast, depth)
        for treegram in treegrams:
            if treegram in treegram_counts:
                treegram_counts[treegram]+=1
                if not make_indicator and treegram in treegram_features[i]:
                    treegram_features[i][treegram]+=1
                else:
                    treegram_features[i][treegram]=1
            else:
                treegram_counts[treegram]=1
                treegram_names.append(treegram)
                treegram_features[i][treegram]=1
    treegram_counts = treegram_counts.values()
    treegram_matrix = np.zeros((len(files), len(treegram_counts)))
    for point in xrange(i):
        for treegram in xrange(len(treegram_names)):
            if treegram_names[treegram] in treegram_features[point]:
                treegram_matrix[point][treegram] = treegram_features[point][treegram_names[treegram]]
    #TODO: Consider sorting by feature? It was broken before.
    return treegram_counts, treegram_names, treegram_matrix

def treegram_string(node, depth):
    s = '('
    s += Node.get_label(node)
    if depth!=1:
        if not Node.get_children(node):
            return None
        else:
            for child in Node.get_children(node):
                t_string = treegram_string(child, depth-1)
                if not t_string:
                    return None
                else:
                    s+=', ' + treegram_string(child, depth-1)
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

def main():
    parser = argparse.ArgumentParser(description='Count treegrams.')
    parser.add_argument('ast_source', nargs='?', default='data/ast', help='location of directory that contains ast files')
    parser.add_argument('-d', '--depth', type=int, default=3, help='depth of subtree considered to be one treegram')
    parser.add_argument('-oc', '--output_counts', default='data/feature/treegram_counts.np', help='name of file to write total treegram counts to')
    parser.add_argument('-on', '--output_names', default='data/feature/treegram_names.np', help='name of file to write treegram names to')
    parser.add_argument('-of', '--output_features', default='data/feature/treegram_features.np', help='name of file to write treegram features to')
    parser.add_argument('-i', '--indicator', action='store_true', help='include this option to make indicator features instead of count features')
    args = parser.parse_args()
    
    source = args.ast_source
    depth = args.depth
    output_counts = args.output_counts
    output_names = args.output_names
    output_features = args.output_features
    make_indicator = args.indicator
    
    treegram_counts, treegram_names, treegram_features = count_treegrams(source, 1, make_indicator)
    for d in xrange(2, depth+1):
        counts, names, features = count_treegrams(source, d, make_indicator)
        treegram_counts = np.append(treegram_counts, counts, 1)
        treegram_names = np.append(treegram_names, names, 0)
        treegram_features = np.append(treegram_features, features, 1)
    
    
    
    np.savetxt(output_counts, treegram_counts)
    np.savetxt(output_features, treegram_features)
    with open(output_names, 'w') as out_file:
        for treegram_name in treegram_names:
            out_file.write('%s\n'%treegram_name)
    
    

if __name__ == '__main__':
    main()
