#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	if not re.search('[a-zA-Z0-9]+', what):
		return "Fine. Be that way!"
	if what.isupper():
		return "Whoa, chill out!"
	if what.strip()[-1] == '?':
		return "Sure."
	else:  # If we made it this far, we do the 'everything else' condition
		return "Whatever."
