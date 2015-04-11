# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
from __future__ import unicode_literals
import re 

def hey(what):

	strip1 = re.sub('[^0-9a-zA-ZäÄÜüÖö]+', '', what)
	strip2 = re.sub('[^a-zA-ZäÄÜüÖö]+', '', what)
	strip3 = re.sub('[^a-zäüö]+', '', what)
	strip4 = re.sub('[^A-ZÄÜÖ]+', '', what)
	strip5 = re.sub('[^0-9]+', '', what)

	if len(strip1) == 0:
		return 'Fine. Be that way!'
	elif what[len(what)-1] == '?':
		if len(strip3) > 0 or len(strip1) == len(strip5):
			return 'Sure.'
		elif len(strip2) == len(strip4):
			return 'Whoa, chill out!'
	elif what[len(what)-1] == '.':
		return 'Whatever.'	
	elif what[len(what)-1] == '!':
		if len(strip3) > 0:
			return 'Whatever.'
		elif len(strip2) == len(strip4) and not len(strip1) == len(strip5):
			return 'Whoa, chill out!'
	else:
		if len(strip4) == len(strip2) and not len(strip1) == len(strip5):
			return 'Whoa, chill out!'
		elif len(strip1) == len(strip5):
			return 'Whatever.'
