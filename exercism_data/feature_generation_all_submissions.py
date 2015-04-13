import argparse
import subprocess
import os


for submission_id in range(0,1000):
		subprocess.call(['python', '../featurization/individual_features.py', 'compute', str(submission_id), 'ruby', 'inherent_features_hamming.np', os.path.abspath("../exercism_data/"), 'flog', 'libcall', 'control_flow', 'duplicate_treegram'])
