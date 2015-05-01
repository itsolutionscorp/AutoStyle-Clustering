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
import re
from individual_features import generate_individual_features, append_at_index

def natural_sort(l): 
    '''
    Sorts numbered filenames by their integer value 
    Ref: http://blog.codinghorror.com/sorting-for-humans-natural-sort-order/
    '''    
    convert = lambda text: int(text) if text.isdigit() else text.lower() 
    alphanum_key = lambda key: [ convert(c) for c in re.split('([0-9]+)', key) ] 
    return sorted(l, key = alphanum_key)

def main():
    '''
    Parse command line arguments, pass them to generate_individual_features,
    and append the given feature vector to an output file.
    '''
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('function_name', help='Name of the function to compute features about.')
    parser.add_argument('language', help='ruby, python, or java')
    parser.add_argument('home_directory', help='Path to data directory')
    parser.add_argument('output_feature_file', help='File to append the generated horizontal feature vector to.')
    parser.add_argument('-l', '--output_line_file', help='File to append the generated horizontal feature line vector to.')
    parser.add_argument('-c', '--class_name', help='Class name (required for java)')
    parser.add_argument('features', nargs='+', help='Names of features to generate.')

    args = parser.parse_args()
    function_name = args.function_name
    features = args.features
    language = args.language
    home_dir = args.home_directory.rstrip("/") + "/"
    output_feature_file = args.output_feature_file
    output_line_file = args.output_line_file
    class_name = args.class_name
    source_files = natural_sort(glob.glob(home_dir + 'src/*'))
    
    all_features = None
    all_lines = None
    for submission_index, submission in enumerate(source_files):
        if submission_index % 100 == 0:
            print submission_index
        feature_vector, feature_lines = generate_individual_features(language, function_name, submission_index, features, home_dir, class_name)
        if all_features is None:
            all_features = feature_vector.T
            all_lines = feature_lines.T
        else:
            all_features = append_at_index(all_features, feature_vector, submission_index)
            all_lines = append_at_index(all_lines, feature_lines, submission_index)
    print all_features.shape
    np.savetxt(output_feature_file, all_features)
    if output_line_file:
        print output_line_file
        np.savetxt(output_line_file, all_lines)

if __name__ == '__main__':
    main()
