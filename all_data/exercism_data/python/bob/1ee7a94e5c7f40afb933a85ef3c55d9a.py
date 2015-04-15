#
# Skeleton file for the Python "Bob" exercise.
#
import re

whiteSpace =r"^[\r\n\s\t]*$"
question =r".+(\?[\r\n\s]*)$"

def hey(what):
		
	if re.match(whiteSpace, what):
		return "Fine. Be that way!"
		
	elif what.isupper():
		return "Whoa, chill out!"

	elif re.match(question, what):
		return "Sure."
		
	else:
		return "Whatever."
