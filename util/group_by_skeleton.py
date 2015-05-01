#!/usr/bin/env python
'''
Created on Apr 27, 2015
Groups source codes based on which ones have the same skeleton code.
@author: jmoghadam
'''
import argparse
import os
import shutil
import glob
import numpy as np
import re
from featurization.individual_features import generate_individual_features, append_at_index, skeleton_from_source, generic_skeleton_from_source

def natural_sort(l): 
    '''
    Sorts numbered filenames by their integer value 
    Ref: http://blog.codinghorror.com/sorting-for-humans-natural-sort-order/
    '''    
    convert = lambda text: int(text) if text.isdigit() else text.lower() 
    alphanum_key = lambda key: [ convert(c) for c in re.split('([0-9]+)', key) ] 
    return sorted(l, key=alphanum_key)

def group_by_generic_skeleton(source_files, output_dir, language, function_name):
    '''
    Put each of source files into a grouping in output_dir based on a more generic skeleton code.
    '''
    skeleton_groups = {}
    for submission_index, submission in enumerate(source_files):
        if submission_index % 100 == 0:
            print submission_index
        skeleton = generic_skeleton_from_source(language, submission)
        print skeleton
        if skeleton not in skeleton_groups:
            skeleton_groups[skeleton] = []
        skeleton_groups[skeleton].append(submission)
    for group_num, skeleton in enumerate(skeleton_groups.keys()):
        folder = output_dir + str(group_num) + '/'
        if not os.path.exists(folder):
            os.makedirs(folder)
        for file in skeleton_groups[skeleton]:
            shutil.copy(file, folder + os.path.basename(file))
            
def group_by_skeleton(source_files, output_dir, language, function_name):
    '''
    Put each of source files into a grouping in output_dir based on their skeleton code.
    '''
    skeleton_groups = {}
    for submission_index, submission in enumerate(source_files):
        if submission_index % 100 == 0:
            print submission_index
        skeleton = skeleton_from_source(language, submission)
        print skeleton
        if skeleton not in skeleton_groups:
            skeleton_groups[skeleton] = []
        skeleton_groups[skeleton].append(submission)
    for group_num, skeleton in enumerate(skeleton_groups.keys()):
        folder = output_dir + str(group_num) + '/'
        if not os.path.exists(folder):
            os.makedirs(folder)
        for file in skeleton_groups[skeleton]:
            shutil.copy(file, folder + os.path.basename(file))


def main():
    '''
    Parse command line arguments, pass them to generate_individual_features,
    and append the given feature vector to an output file.
    '''
    parser = argparse.ArgumentParser(description='Generate a feature vector for a particular submission.')
    parser.add_argument('function_name', help='Name of the function to compute features about.')
    parser.add_argument('language', help='ruby or python')
    parser.add_argument('home_directory', help='Path to data directory')
    parser.add_argument('output_directory', help='Directory to store grouped files in.')

    args = parser.parse_args()
    function_name = args.function_name
    language = args.language
    home_dir = args.home_directory.rstrip("/") + "/"
    output_dir = args.output_directory.rstrip("/") + "/"
    source_files = natural_sort(glob.glob(home_dir + 'src/*'))
    
    group_by_generic_skeleton(source_files, output_dir, language, function_name)
    
if __name__ == '__main__':
    main()
