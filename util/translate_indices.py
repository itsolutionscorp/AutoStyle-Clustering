#!/usr/bin/env python
'''
Created on Oct 25, 2014

@author: joe
'''
import argparse
import numpy as np

def translate_features(data, i1, i2):
    '''Warning: This is a quadratic algorithm. 
    It can easily be replaced by a linear one, if you care, 
    since both i1 and i2 should be sorted.
    '''
    out = np.zeros((i2.shape[0], data.shape[1]))
    for name in i1:
        out[np.where(i2==name), :] = data[np.where(i1==name), :]
    return out

def main():
    
    parser = argparse.ArgumentParser(description='Take features associated with one index and transform it to another.')
    parser.add_argument('data', help='location of file that contains features')
    parser.add_argument('index1', help='index file the features currently correspond to')
    parser.add_argument('index2', help='index file to translate features to')
    parser.add_argument('output_file', help='name of output feature file')
    args = parser.parse_args()
    data = np.loadtxt(args.data)
    i1 = np.loadtxt(args.index1)
    i2 = np.loadtxt(args.index2)
    out = args.output_file
    
    translated_features = translate_features(data, i1, i2)
    np.savetxt(out, translated_features)

if __name__=='__main__':
    main()
