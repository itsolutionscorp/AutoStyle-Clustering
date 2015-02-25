'''
Created on Feb 25, 2015

@author: jmoghadam
'''
import glob
import numpy as np
from count_treegrams import get_treegrams
from syntax_tree.tree import control_flow_and_library_tree

def contains_duplicates(l):
    return len(l) != len(set(l))

def duplicate_treegram_features(source):
    files = glob.glob(source + '/*_ast')
    files.sort()
    n = len(files)
    has_duplicates = np.zeros((n, 1)) 
    for i, filename in enumerate(files):
        ast = control_flow_and_library_tree(filename, 'combine_anagrams')
        treegrams = get_treegrams(ast, 3)
        if contains_duplicates(treegrams):
            has_duplicates[i, 0] = 1
    return has_duplicates
            
def main():
    duplicate_feature = duplicate_treegram_features('./data/ast/')
    np.savetxt('./data/feature/duplicate_treegram_features.np', duplicate_feature)
    with open('data/feature/duplicate_treegram_names.np', 'w') as f:
        f.write('duplicate_treegrams' + '\n')
            
if __name__ == '__main__':
    main()
        
