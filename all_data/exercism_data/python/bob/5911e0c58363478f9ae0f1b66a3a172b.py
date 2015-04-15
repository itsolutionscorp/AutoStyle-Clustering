def hey(arg):

	if not arg or arg.isspace():
		return "Fine. Be that way!"

	elif arg.isupper(): 
		return "Whoa, chill out!"

	elif arg[-1]=="?":
		return "Sure."

	return "Whatever."
	
