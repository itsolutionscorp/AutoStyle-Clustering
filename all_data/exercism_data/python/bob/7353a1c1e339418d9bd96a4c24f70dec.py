#
# Skeleton file for the Python "Bob" exercise.
#
import re
def hey(what):
	match = re.search(r'[a-zA-Z]',what)
	omatch = re.search(r'[\t\n\r\f\v]',what)

	if what.lower() == "bob" or what.lower() == "" or omatch:
		return "Fine. Be that way!"

	if what == what.upper() and match:
		return "Woah, chill out!"
	
	if what[-1] == '?':
		return "Sure."

	else:
		return "Whatever."
