def hey(what):
	what=what.strip()
	#what will be an empty string if there are only whitespace characters
	if not what:
		return 'Fine. Be that way!'
	elif what.isupper():
		return 'Whoa, chill out!'
	elif what.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
