#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
	if what.find("?") == 1:
		return "Sure."
	elif (what.find("!") == 1) or what.isupper():
		return "Whoa, chill out!"
	elif what == "" or what is None:
		return "Fine. Be that way!"
	return "Whatever."
