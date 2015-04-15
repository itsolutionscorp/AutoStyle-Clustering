#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	if re.search('^\s*.*[a-z\d]+.*\s*\?\s*$', what):
		return 'Sure.'
	elif re.search('[a-z\u00E0-\u00FF]+', what):
		return 'Whatever.'
	elif re.search('^[^a-z]+[A-Z]+[^a-z]+$', what):
		return 'Whoa, chill out!'
	elif re.search('\d+[^A-Za-z]+', what):
		return 'Whatever.'
	elif re.search('^\s*$', what):
		return 'Fine. Be that way!'
