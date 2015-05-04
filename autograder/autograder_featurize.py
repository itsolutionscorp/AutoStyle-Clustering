#!/usr/bin/python -tt
"""
Filename: autograder_featurize.py
About: For one submission, generates AST, computes edit distance to all other submissions, and adds this to the distance matrix, and computes the submission's feature vector.
	   Requires a data directory with an src/ folder that contains submissions numbered and sorted numerically (i.e. 0.rb, 1.rb, 2.rb...).
	   Used by controller.py when a new submission comes in.
Author: Mukund Chillakanti

TODO: Currently specific to ruby datasets. Need to provide option for Python, Java AST generation.
"""

import sys
sys.path.append("../syntax_tree")
import pdb
import tree
import numpy as np
import zss # Install: pip install zss
import subprocess
import os
import pp
import ast
import betterast
from collections import defaultdict


def generateRubyAST (data_dir, submission_id, method):
	'''
	Generates a Ruby submission's AST and stores it in the ast/ folder inside data_dir. 
	This function call a ruby file (source_to_ast_one_submission.rb) which does the generation.
    @param data_dir: the data directory that contains src/ with the submissions
	'''
 	source_file_for_ast = data_dir + 'src/' + str(submission_id) + '.rb'
 	output_directory_for_ast = data_dir + 'ast'
  	method_for_ast = method
 	subprocess.call(['ruby', './source_to_ast_one_submission.rb', '-s', source_file_for_ast, '-o', output_directory_for_ast, '-m', method_for_ast])
  	list_of_submissions = range(0, submission_id+1)
  	ast_folder = "../autograder/" + data_dir + "ast"
  	trees = construct_trees(ast_folder, list_of_submissions, method)
  	return trees

def construct_trees(input_dir, indexes, method):
  '''
  Generates a Ruby submission's AST tree
  @param input_dir: the data directory that contains src/ with the submissions
  @param indexes: the list of submissions numbers to generate trees for
  @param method: the method in these submissions to generate trees for
  '''
  trees = []
  for i in indexes:
    trees.append(tree.abstract_syntax_tree(input_dir + "/" + str(i) + "_ast", method))
  return trees


def compute_ast_and_dist_matrix(data_dir, submission_id, method, language):
	'''
	For one submission, generates AST, computes edit distance to all other submissions, and adds this to the distance matrix.
	@param data_dir: the data directory that contains src/ with the submissions
	@param submission_id: the submission to perform the edit distance computation for
	@param method: the relevant method in the submissions
	'''
	autograde_folder = "autograder/"
	featurize_folder = "../featurization/"
	
	#generate AST (add other languages and the calls to their respective AST generation methods here)
	if language == 'ruby':
		trees = generateRubyAST(data_dir, submission_id, method)

  	#add to the distance matrix if it exists, otherwise create the distance matrix file and add the first entry
	if os.path.exists(data_dir + 'gen/ast_dist_matrix.np'):
		distances = np.loadtxt(data_dir + 'gen/ast_dist_matrix.np', ndmin=1)
		job_server = pp.Server(8)
		jobs = []
		values_to_add = []
		next_index = distances.shape[0]
		for i in range(0, len(distances)):
			jobs.append((i, job_server.submit(tree_distance, (trees[i], trees[next_index]), (), ("zss",))))
		for i, job in jobs:
			values_to_add.append((i,job))

		values_to_add = sorted(values_to_add, key=getKey)
		values_to_add = [x[1]() for x in values_to_add]
		distances = np.column_stack((distances, values_to_add))
		values_to_add.append(tree_distance(trees[next_index], trees[next_index]))
		distances = np.vstack((distances,values_to_add))
		np.savetxt(data_dir+'gen/ast_dist_matrix.np', distances, delimiter=" ", fmt="%d")
	else:
		distances = np.zeros((1,1))
		distances[0][0] = tree_distance(trees[submission_id], trees[submission_id])
		if not os.path.exists(data_dir + 'gen'):
   			 os.makedirs(data_dir + 'gen')
		np.savetxt(data_dir + 'gen/ast_dist_matrix.np', distances, delimiter=" ", fmt="%d")

	subprocess.call(['python', '../featurization/individual_features.py', method, str(submission_id), 'ruby', 'individual_features_test.np', os.path.abspath("./data/"), 'flog', 'libcall', 'control_flow', 'duplicate_treegram'])
 	
def getKey(item):
    return item[0]

def tree_distance(tree1, tree2):
  return zss.simple_distance(tree1, tree2)

if __name__ == '__main__':
	os.system("rm -rf ./data/ast/")
	os.system("rm -rf ./data/gen/")
	for i in range(0,10):
		compute_ast_and_dist_matrix("./data/", i, 'combine_anagrams', 'ruby')

