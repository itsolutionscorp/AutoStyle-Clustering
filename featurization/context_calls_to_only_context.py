#!/usr/bin/env python

'''
Created on Feb 11, 2015
Script that takes features that measure usage of library calls 
inside explicit iteration and/or conditionals and creates
features only about usage of explicit iteration and/or
conditionals.
@author: jmoghadam
'''

import numpy as np

def context_calls_to_only_context(context_calls_vector, context_calls_names):
    n, m = context_calls_vector.shape
    translation_vector = np.zeros((m, 4))
    for j in xrange(m):
        feature = context_calls_names[j]
        items = feature.strip('[]').split(', ')
        if len(items) < 1:
            continue
        context = items[1:]
        num_conditionals = context.count('1')
        num_iterations = context.count('2') 
        if num_conditionals > 0:
            translation_vector[j, 0] = 1
        if num_conditionals > 1:
            translation_vector[j, 1] = 1
        if num_iterations > 0:
            translation_vector[j, 2] = 1
        if num_iterations > 1:
            translation_vector[j, 3] = 1
    only_context_vector = np.zeros((n, 4))
    for i in xrange(n):
        for j in xrange(m):
            if context_calls_vector[i, j] > 0:
                only_context_vector[i, :] = only_context_vector[i, :] + translation_vector[j]
    only_context_vector[only_context_vector > 0] = 1
    return only_context_vector
            
    
def main():
    ccalls_vector = np.loadtxt('data/feature/context_calls.np')
    ccalls_names = np.genfromtxt('data/feature/context_calls_names.np', dtype='str', delimiter='\n')
    only_context_vector = context_calls_to_only_context(ccalls_vector, ccalls_names)
    only_context_names = ['conditional', 'nested conditionals', 'explicit iteration', 'nested explicit iteration']
    np.savetxt('data/feature/only_context_features.np', only_context_vector)
    with open('data/feature/only_context_names.np', 'w') as f:
        for name in only_context_names:
            f.write(name + '\n')
if __name__ == '__main__':
    main()