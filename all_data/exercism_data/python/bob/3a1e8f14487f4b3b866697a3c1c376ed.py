#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if(what.isupper()):
		return "Whoa, chill out!"
	if(what.endswith('?')):
		return "Sure."
	if(len(what)==0 or what.isspace()):
		return "Fine. Be that way!"
	return "Whatever."
