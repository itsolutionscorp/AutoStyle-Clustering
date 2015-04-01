#!/usr/bin/env python

'''
Created on Oct 8, 2014
File for generating various feature vectors, using python
@author: joe
'''

import glob
import numpy as np
import re
import os
import pickle
import subprocess
import json
from util.ruby_doc_parser import RubyDocPageParser
from count_treegrams import count_treegrams

def style_score(style_features):
    '''Add together all the style scores in style_features into a single style score'''
    #TODO: Allow for weights
    return np.sum(style_features, 1)   

def manual_features(ast_dir):
        files = glob.glob(ast_dir + '/*_ast')
        files.sort()
        n = len(files)
        features = np.zeros(n)
        for i in xrange(n):
            with open(files[i]) as f:
                source_code = f.read()
                if not re.search('group_by', source_code):
                    features[i]+=1
                    if not re.search('values', source_code):
                        features[i]+=1
                    if not re.search('Hash', source_code) and not re.search('hash', source_code):
                        features[i]+=1
        return features
    
def data_structure_features(ast_dir, data_structures):
    """
    Indicates usage of methods that produce the given data structures.
    """        
    
    if not os.path.isfile('util/lib_call_dict.pkl'):
        p = RubyDocPageParser()
        p.create_lib_call_dict('util/ruby_modules.txt')
    with open('util/lib_call_dict.pkl', 'r') as f:
        lib_call_dict = pickle.load(f)
        
    libcall_counts, libcall_names, libcall_features = count_treegrams('data/ast', 1, True)
    data_structure_feature_names = set()
    for libcall in libcall_names:
        libcall = libcall[1:-1] #Strip off parens from treegram
        if libcall in lib_call_dict:
            for result in lib_call_dict[libcall]:
                if result in data_structures:
                    data_structure_feature_names.add('-> ' + result)
    
    data_structure_feature_names = list(data_structure_feature_names)
    n = libcall_features.shape[0] #number of points
    m = len(data_structure_feature_names)
    data_structure_feature_indices = dict(zip(data_structure_feature_names, np.linspace(0, m, m+1)))
    
    #This is the worst code I have written
    data_structure_features = np.zeros((n, m))
    for i in xrange(n):
        for j in xrange(libcall_features.shape[1]):
            if libcall_features[i, j] > 0:
                libcall = libcall_names[j] #Iterate over each possible library call
                libcall = libcall[1:-1] #Strip off parens from treegram
                if libcall in lib_call_dict:
                    for result in lib_call_dict[libcall]: #Iterate over its possible return types
                        if result in data_structures:
                            if i == 523:
                                print libcall + ': ' + result
                            data_structure_index = data_structure_feature_indices['-> ' + result] #Get the index of the return type in our array
                            data_structure_features[i, data_structure_index] = 1
                            
    #Explicit instantiation features
    files = glob.glob(ast_dir + '/*_ast')
    files.sort()
    n = len(files)
    for i in xrange(n):
        with open(files[i]) as f:
            source_code = f.read()
            for ds in data_structures:
                if len(re.findall(ds, source_code)) > 0:
                    data_structure_index = data_structure_feature_indices['-> ' + ds]
                    data_structure_features[i, data_structure_index] = 1
            
    return data_structure_features, data_structure_feature_names    

def libcall_and_linenum_features(src_dir):
    new_data_structures = set(['array', 'hash']) #names for creating new hashes and arrays. Should not be added as library calls
    files = glob.glob(src_dir + '/*.rb')
    files.sort()
    m = len(files)
    
    #TODO: awkwardly hardcoded to location of the ruby script
    #Assumes you're running this script from directory above featurization
    subprocess.call(['./featurization/libcalls_and_linenums.rb', src_dir, 'featurization/libcalls_and_linenums.json'])
    with open('featurization/libcalls_and_linenums.json') as json_data:
        libcalls_and_linenums = json.load(json_data)
    all_libcalls = set()
    for libcall_dict in libcalls_and_linenums:
        all_libcalls.update(libcall_dict.keys())
    all_libcalls = list(all_libcalls)
    n = len(all_libcalls)
    
    features = np.zeros((m, n))
    for i, libcall_dict in enumerate(libcalls_and_linenums):
        for libcall in libcall_dict:
            if libcall not in new_data_structures:
                features[i][all_libcalls.index(libcall)] = 1
    
    np.savetxt('data/feature/libcall_features.np', features)
    with open('data/feature/libcall_names.np', 'w') as out_file:
        for libcall in all_libcalls:
            #if libcall not in new_data_structures:
            out_file.write('%s\n'%libcall)
    
    
def create_data_structure_features():
    interesting_data_structures = set(['hash', 'array', 'enumerator', 'string', 'regexp', 'integer'])
    structure_features, names = data_structure_features('data/ast', interesting_data_structures)
    np.savetxt('data/feature/data_structure_features.np', structure_features)
    with open('data/feature/data_structure_names.np', 'w') as out_file:
        for name in names:
            out_file.write('%s\n'%name)
    
    style_features = np.loadtxt('data/feature/inherent_style_features.np')
    style_score_features = style_score(style_features)
    np.savetxt('data/feature/style_scores.np', style_score_features)
    
def create_libcall_and_linenum_features():
    libcall_and_linenum_features('data/feature/method_source')
    
def main():
    #create_data_structure_features()
    create_libcall_and_linenum_features()
        
if __name__=='__main__':
    main()
                    
