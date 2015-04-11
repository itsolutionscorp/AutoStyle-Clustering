#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
	if (what.isupper()):
		res = "Whoa, chill out!"
	elif (re.match(r'.*\?$', what)): 
		res = "Sure."
	elif (re.match(r'^\s*$', what)):
		res = "Fine. Be that way!"
	else:
		res = "Whatever."

	return res
