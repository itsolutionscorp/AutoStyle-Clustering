def hey(input):
	if input.strip() == '':
		return 'Fine. Be that way!'
	elif input.isupper():
		return 'Woah, chill out!'
	elif input.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
