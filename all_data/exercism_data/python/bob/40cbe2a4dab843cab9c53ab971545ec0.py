#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# Strip trailing white space
	cleanwhat = what.rstrip()
	
	# Is nothing there?
	if (cleanwhat == ""):
		return "Fine. Be that way!"
	# Is it all caps? Figure they are shouting.	
	elif (cleanwhat.isupper()):
		return "Whoa, chill out!"
	# Questions usually end with a ?
	elif (cleanwhat.endswith("?")):
		return "Sure."
	# If it does not get caught above, drop the W bomb
	else: 
		return "Whatever."
