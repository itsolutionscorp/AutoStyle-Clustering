#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	# Testing empty strings and trailinig spaces
	if what.strip() == '':
		return "Fine. Be that way!"
	# Testing if string is uppercase
	elif what.isupper():
		return "Whoa, chill out!"
	# Testing if string ends with ?
	elif what.strip().endswith("?"):
		return "Sure."
	# Everything else
	else:
		return "Whatever."
