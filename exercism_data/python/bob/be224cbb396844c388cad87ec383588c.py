def hey(arg):
	#Checks empty string.
	if not arg.strip():
		return 'Fine. Be that way!'
	elif arg.isupper():
		return "Whoa, chill out!"
	elif arg.endswith('?'):
		return "Sure."
	else:
		return "Whatever."
