def hey(message):
	#screaming check has to be first
	if message.isupper():
		return 'Whoa, chill out!'
	if message.endswith('?'):
		return 'Sure.'
	if message is None or message.strip() == '':
		return 'Fine. Be that way!'	
	return 'Whatever.'
