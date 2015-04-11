def hey(input):
	if not input.strip():
		return 'Fine. Be that way!'

	elif input == input.upper() and input != input.lower():
		return 'Whoa, chill out!'

	elif input.endswith('?'):
		return 'Sure.'

	else:
		return 'Whatever.'
