# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.


import re

def hey(what):
	#Remove white spaces in the string
	
	what = what.strip()

	if re.search('äÜ', what):
		return "Whatever."
	#Yeling at bob
	elif re.search('!$', what) or re.search("[A-Z][A-Z][A-Z][A-Z]", what):
		return "Whoa, chill out!"
	#Making questions
	elif re.search('\?$', what):
		return "Sure."
	#Long silence
	elif len(what) == 0:
		return "Fine. Be that way!"
	else:
		return "Whatever."

print hey('ÜMLäÜTS!')
