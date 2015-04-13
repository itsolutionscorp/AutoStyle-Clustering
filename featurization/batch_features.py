#!/usr/bin/env python
'''
Created on Mar 30, 2015
Generates all the features corresponding to all submissions in a directory.
This is a script that just calls individual_features.py on all submissions in
a directory.
In order to run this file, you must have:
    * A directory containing all the source files.
    * A directory containing the ast of each source file (for ruby).
    * A list of all library calls.
@author: jmoghadam
'''
import argparse
import glob
import numpy as np
from individual_features import generate_individual_features, append_at_index

def main():
    '''
    Parse command line arguScriments, pass them to generate_individual_features,
    and append the given feature vector to an output file.
    '''
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('function_name', help='Name of the function to compute features about.')
    parser.add_argument('language', help='ruby or python')
    parser.add_argument('output_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('home_directory', help='Path to data directory')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    function_name = args.function_name
    features = args.features
    language = args.language
    home_dir = args.home_directory.rstrip("/") + "/"
    output_file = args.output_file
    source_files = sorted(glob.glob(home_dir + 'src/*'))
    
    all_features = None
    for submission_index, submission in enumerate(source_files):
        feature_vector = generate_individual_features(language, function_name, submission_index, features, home_dir)
        if all_features is None:
            all_features = feature_vector.T
        else:
            all_features = append_at_index(all_features, feature_vector, submission_index)
    np.savetxt(output_file, all_features)

if __name__ == '__main__':
    main()
