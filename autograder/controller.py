import os
import sys
import autograder_featurize
import hint_generator
import json

def controller(src_dir_path, min_submissions, ast_dif, flog_diff):
	existing = set()
	queue = []
	while (1):
		files = set(os.listdir(src_dir_path))
		diff = files.difference(existing)
		for filename in diff:
			submission_id = filename.strip(".rb")   # THIS DOES NOT CORRESPOND TO THE START INDEX in style_chain -- problem
			#call autograd_featurize.py function with submission id
			queue.append(submission_id)
			if len(existing) > min_submissions:
				hints = hint_generator.generate_hint(submission_id, ast_dif, flog_diff)
				with open(src_dir_path+"/" +filename + "_hints.json", 'r') as f:
					json.dump(hints, f)
				f.close()
		existing = existing.union(diff)

if __name__ == '__main__':
	if len(sys.argv) != 5:
		controller("../data/src/", 200, 0, 2)
	else:
		controller(sys.argv[1].rstrip("/"), sys.argv[2],sys.argv[3],sys.argv[4])