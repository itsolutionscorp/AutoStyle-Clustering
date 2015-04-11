#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if(len(what.rstrip())==0):
		return "Fine. Be that way!"
	if(what.isupper()):
		return "Whoa, chill out!"
	if(what.rstrip().find("?") == len(what.rstrip())-1) :
		return "Sure."
	else:
		return "Whatever."
