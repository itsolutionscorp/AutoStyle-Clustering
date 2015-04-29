def hey(message):

	if message.isupper() or u'\xc3' in message:
		return 'Whoa, chill out!'
	elif message.endswith('!') and message.islower():
		return 'Whatever.'
	elif message.endswith('?'):
		return 'Sure.'
	elif not message or not message.strip():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
