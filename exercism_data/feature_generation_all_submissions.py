import argparse
import subprocess
import os

for submission_id in range(0,986):
		subprocess.call(['python', '../featurization/individual_features.py', 'compute', str(submission_id), 'ruby', 'hamming/feature/inherent_style_features.np', os.path.abspath("./hamming/"), 'flog'])
		subprocess.call(['python', '../featurization/individual_features.py', 'compute', str(submission_id), 'ruby', 'hamming/feature/instrumental_style_features.np', os.path.abspath("./hamming/"), 'libcall', 'control_flow', 'duplicate_treegram'])
  