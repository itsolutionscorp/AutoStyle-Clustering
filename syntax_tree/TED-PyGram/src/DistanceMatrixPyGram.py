'''
The file uses PQ-Gram edit distance to compute and print the edit a distance matrix
between all submissions that have an AST.

Run this file with 2 parameteres, the data_directory, and the method for which hints are being generate.
Example: python TreeEditDistancePyGram.py data/ combine_anagrams
'''

import sys
import os
sys.path.append("../syntax_tree")
import tree
import PyGram
import argparse
import numpy as np

def main():
	parser = argparse.ArgumentParser()
	parser.add_argument('data_directory', help='Directory for original data')
        parser.add_argument('output_file', help='Name of file to save the matrix in')
        parser.add_argument('file_name', help='Name of the java file')
	parser.add_argument('method', help='Method')

	args = parser.parse_args()
	data_directory = args.data_directory
        output_file = args.output_file
        file_name = args.file_name
	method = args.method
	ast_folder = data_directory + 'ast/'

        n = len(os.listdir(ast_folder))
        matrix = np.zeros((n, n))
        for i, current in enumerate(os.listdir(ast_folder)):
            t = tree.abstract_syntax_tree(os.path.join(ast_folder, current, file_name + '.ast'), method)
            currentPygram = PyGram.Profile(t)
            for j, other in enumerate(os.listdir(ast_folder)):
                t = tree.abstract_syntax_tree(os.path.join(ast_folder, other, file_name + '.ast'), method)
                pygram = PyGram.Profile(t)
                matrix[i, j] = currentPygram.edit_distance(pygram)
        np.savetxt(output_file, matrix) 


if __name__ == '__main__':
	main()
