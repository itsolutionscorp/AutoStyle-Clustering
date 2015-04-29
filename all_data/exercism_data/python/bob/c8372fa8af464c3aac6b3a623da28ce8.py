def hey(message):
	if not message or message.isspace():
		return 'Fine. Be that way!'
	if message.isupper():
		return 'Woah, chill out!'
	if message[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
