def hey(arg):
	if arg.isupper():
		return 'Woah, chill out!'
	elif arg.endswith('?'):
		return 'Sure.'
	elif arg.strip() == "":
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
