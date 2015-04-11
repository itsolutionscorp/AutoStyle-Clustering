def hey(arg):
	if arg.isspace() or len(arg) == 0:
		return "Fine. Be that way!"
	
	elif arg.isupper():
		return "Woah, chill out!"
		
	elif arg[-1] == "?":
		return "Sure."
	
	else:
		return "Whatever."
