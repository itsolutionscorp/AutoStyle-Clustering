def hey(argument):
	
	string = argument.strip()

	if not string:
		response = 'Fine. Be that way!'

	elif string.isupper():
		response = 'Whoa, chill out!'
	
	elif string.endswith('?'):
		response = 'Sure.'

	else:
		response = 'Whatever.'

	return response
