def hey(phrase):
	if not phrase or phrase.isspace():
		return 'Fine. Be that way!'
	elif phrase.isupper():
		return 'Whoa, chill out!'
	elif phrase.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
