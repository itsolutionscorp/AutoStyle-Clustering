"""
Filename: hint_generator.py
About: In this file, generate_hint generates hints for the one submission that is passed in as parameter along with flog and ast thresholds.
	   Makes a call to generate_chain in style_chain.py which contains the bulk of the hint generation logic.
	   This script is used by controller.py to generate hints for a submission.
"""

import os
import sys
sys.path.insert(0, os.path.abspath('../visualization/'))
from style_chain import generate_chain, interpret_list_of_hints

def generate_hint(submission_id, ast_dif, flog_diff):
	cl = generate_chain(submission_id, ast_dif, flog_diff, os.path.abspath('../') + "/").head
	if cl.next:
		hints = []
		pos_hints = cl.get_positive_hint()
		neg_hints = cl.get_negative_hint()
		for ph in pos_hints[0]:
			hints.append(ph)
		for nh in neg_hints[0]:
			hints.append(nh)
		return {'code': cl.source_code, 'positive_hint': interpret_list_of_hints(pos_hints[0], False).split("\n")[1:-1], 'positive_lines': pos_hints[1], 'negative_lines': neg_hints[1], 'negative_hint':interpret_list_of_hints(neg_hints[0], True).split("\n")[1:-1]}
	else:
		return {'code': cl.source_code, 'positive_hint': "", 'negative_hint': ""}