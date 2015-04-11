def hey(call):
	if call.strip() == '':
		return 'Fine. Be that way!'
	elif call.isupper():
		return 'Whoa, chill out!'
	elif call.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
