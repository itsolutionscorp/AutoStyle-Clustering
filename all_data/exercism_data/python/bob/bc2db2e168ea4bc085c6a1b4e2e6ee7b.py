def hey(message=None):
	#if message.endswith('?'):
	#	return 'Sure.'
	if message.isupper():
		return 'Woah, chill out!'
	if message.endswith('?'):
		return 'Sure.'
	if message.strip() == '':
		return 'Fine. Be that way!'

	return 'Whatever.'
