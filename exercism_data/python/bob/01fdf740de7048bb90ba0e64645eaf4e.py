#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	
	if what.endswith("?") and not what.isupper():
		return "Sure."
	elif what.isupper():
		return "Whoa, chill out!"
	elif not what or what.isspace():
		return "Fine. Be that way!"
	else:
		return "Whatever."
