def hey(quote=''):
	if quote.strip() == '':
		return 'Fine. Be that way!'
	if quote.isupper():
		return 'Woah, chill out!'
	if quote.endswith('?'):
		return 'Sure.'
	return 'Whatever.'
