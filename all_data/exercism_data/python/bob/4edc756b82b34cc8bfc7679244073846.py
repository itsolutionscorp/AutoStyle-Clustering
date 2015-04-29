def hey(text):
	if text.strip() == '':
		return 'Fine. Be that way!'
	if text.isupper():
		return 'Woah, chill out!'
	if text.endswith('?'):
		return 'Sure.'
	return 'Whatever.'
