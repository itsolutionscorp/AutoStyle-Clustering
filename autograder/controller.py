"""
Filename: controller.py
About: This controller simulates the behavior of an autograder. When run, it constantly checks if a directory
of submissions has reached a minimum threshold number of submissions. If so, it generates hints and writes them to a file
for all existing submissions and any new submissions that come into the folder after the threshold has already been reached.

TODO: Currently specific to ruby datasets.
"""


import os
import sys
import autograder_featurize
import hint_generator
import json
import shutil

def controller(src_dir_path, autograde_src_path, min_submissions, ast_dif, flog_diff):
	'''
	@param src: the src directory where submissions come in
	@param autograde_src_path: the directory to place submissions from the src_dir_path but renamed to be in numerical sorted order.
	@param min_submissions: minimum threshold of submissions required before generating hints
	@param ast_dif: ast difference threshold
	@param flog_diff: flog score threshold
	'''
	existing = set()
	queue = []
	mapfile = open("mapping.csv", "a")
	while (1):
		files = set(os.listdir(src_dir_path))
		diff = files.difference(existing)
		for filename in diff:
			submission_id = filename.strip(".rb")  
			index_id = len(os.listdir(autograde_src_path))
			mapfile.write(submission_id,index_id)
			shutil.copyfile(src_dir_path +"/"+filename , autograde_src_path + "/"+index_id)
			#call autograde_featurize.py function with index_id
			queue.append(index_id)
			if len(existing) > min_submissions:
				hints = hint_generator.generate_hint(index_id, ast_dif, flog_diff)
				with open(src_dir_path+"/" +index_id + "_hints.json", 'r') as f:
					json.dump(hints, f)
				f.close()
		existing = existing.union(diff)

if __name__ == '__main__':
	if len(sys.argv) != 5:
		controller("../data/src/", "./data/",200, 0, 2)
	else:
		controller(sys.argv[1].rstrip("/"), sys.argv[2].rstrip("/"),sys.argv[3],sys.argv[4], sys.argv[5])