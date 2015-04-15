def hey(some_string):

	if some_string.isupper():
		return 'Whoa, chill out!'
	elif some_string.isspace() or some_string == '':
		return 'Fine. Be that way!'
	elif some_string.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
