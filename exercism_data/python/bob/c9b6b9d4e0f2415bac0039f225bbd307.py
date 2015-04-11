#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	# Testing empty strings with tralinig spaces
	if what.strip() == '':
		return "Fine. Be that way!"
	# Testing string has letters and if they are all Uppercase
	elif what == what.upper() and bool(re.search('[a-zA-Z]', what)):
		return "Whoa, chill out!"
	# Testing whether string ends witch ? including trailng spaces
	elif what.strip()[-1] == "?":
		return "Sure."
	# Everyting esle
	else:
		return "Whatever."
