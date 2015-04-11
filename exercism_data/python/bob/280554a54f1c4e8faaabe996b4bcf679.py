def hey(message):
	if message.isupper():
		return 'Woah, chill out!'

	elif message.endswith('?'):
		return 'Sure.'

	elif message == '' or message[0:].isspace():
		return 'Fine. Be that way!'
	
	else:
		return 'Whatever.'
