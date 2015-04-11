#
# Skeleton file for the Python "Bob" exercise.
#
import unicodedata

def hey(what):
	what = what.strip()
	if not len(what):
		return "Fine. Be that way!"
	elif what.isupper():
		return "Whoa, chill out!"
	elif what[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
