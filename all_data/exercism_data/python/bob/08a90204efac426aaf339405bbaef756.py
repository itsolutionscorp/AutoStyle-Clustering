import re
def hey(quote=''):
	if quote.strip() == '':
		return 'Fine. Be that way!'
	if quote == quote.upper() and bool(re.search('[a-zA-Z]', quote)):
		return 'Woah, chill out!'
	if quote.endswith('?'):
		return 'Sure.'
	return 'Whatever.'
