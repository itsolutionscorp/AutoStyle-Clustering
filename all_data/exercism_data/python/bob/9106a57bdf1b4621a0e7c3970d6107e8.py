#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	strippedWhat = what.rstrip()
	#print "/%s/" % strippedWhat

	if strippedWhat.isupper():
		return "Whoa, chill out!"			
	elif strippedWhat.endswith("!") and hasSpecialCharacter(strippedWhat):
		return "Whoa, chill out!"	
	elif  strippedWhat.endswith("?"):
		return "Sure."
	if len(strippedWhat) == 0:
		return "Fine. Be that way!"
	else:
		return "Whatever."


def hasSpecialCharacter(what):
	#print "/%s/" % what
	result = True
	for index in what:
		if ord(index) < 65:
			result = True
		else:
			return False
	print result
	return result
