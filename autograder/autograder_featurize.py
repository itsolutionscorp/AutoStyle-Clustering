#!/usr/bin/python -tt

import sys
sys.path.append("../syntax_tree")
import pdb
import tree
import numpy as np
import zss # Install: pip install zss
# import pp
import time
import subprocess
import os
import pp

def compute_ast_and_dist_matrix(autograde_src_path, submission_id):
	data_dir = "data/"
	autograde_folder = "autograder/"
	featurize_folder = "../featurization/"
	

 	#generate a submissions's ast 
 	source_file_for_ast = autograde_src_path + str(submission_id) + '.rb'
 	output_directory_for_ast = data_dir + 'ast'
  	method_for_ast = 'combine_anagrams'
 	subprocess.call(['ruby', './source_to_ast_one_submission.rb', '-s', source_file_for_ast, '-o', output_directory_for_ast, '-m', method_for_ast])


 	#construct trees for all submissions so far from their ast's
  	list_of_submissions = range(0, submission_id+1)
  	ast_folder = "../autograder/" + data_dir + "ast"
  	trees = construct_trees(ast_folder, list_of_submissions)
  	#TODO: STORE THE TREES TO AVOID RECOMPUTATION
  	
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

	subprocess.call(['python', '../featurization/individual_features.py', 'combine_anagrams', str(submission_id), 'ruby', 'individual_features_test.np', os.path.abspath("./data/"), 'flog', 'libcall', 'control_flow', 'duplicate_treegram'])
 	
def getKey(item):
    return item[0]

def construct_trees(input_dir, indexes):
  trees = []
  for i in indexes:
    trees.append(tree.abstract_syntax_tree(input_dir + "/" + str(i) + "_ast", "combine_anagrams"))
  return trees

def tree_distance(tree1, tree2):
  return zss.simple_distance(tree1, tree2)


if __name__ == '__main__':
	os.system("rm -rf ./data/ast/")
	os.system("rm -rf ./data/gen/")
	for i in range(0,10):
		compute_ast_and_dist_matrix("./data/src/", i)

