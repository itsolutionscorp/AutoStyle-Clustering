'''
The file uses PQ-Gram edit distance to compute and print the edit distances 
between the latest submission for which an AST was generated and
all previous submissions. You can use/modify this code appropriately to use PyGram 
for the distance matrix computation, for faster results.

Run this file with 2 parameteres, the data_directory, and the method for which hints are being generate.
Example: python TreeEditDistancePyGram.py data/ combine_anagrams
'''

import sys
import os
sys.path.append("../syntax_tree")
import tree
import PyGram
import argparse

def main():
	parser = argparse.ArgumentParser()
	parser.add_argument('data_directory', help='Directory for original data')
	parser.add_argument('method', help='Method')

	args = parser.parse_args()
	data_directory = args.data_directory
	method = args.method
	ast_folder = data_directory + 'ast/'

	latest = len(os.listdir(ast_folder))-1
	latestPygram = PyGram.Profile(tree.abstract_syntax_tree(ast_folder + str(latest) + "_ast", method))

	pygrams = []
	for i in range(len(os.listdir(ast_folder))-1):
	    t = tree.abstract_syntax_tree(ast_folder + str(i) + "_ast", method)
	    pygrams.append(PyGram.Profile(t))

	distances = []
	for i in range(latest):
		distances.append(latestPygram.edit_distance(pygrams[i]))

	print distances

if __name__ == '__main__':
	main()
