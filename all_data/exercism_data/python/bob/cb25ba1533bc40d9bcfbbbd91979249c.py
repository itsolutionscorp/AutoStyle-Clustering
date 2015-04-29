def hey(phrase):
	if(phrase.isspace() or not phrase):
		return 'Fine. Be that way!'
	elif (phrase.isupper()):
		return 'Whoa, chill out!'
	elif (phrase.endswith('?')):
		return 'Sure.'
	else:
		return 'Whatever.'
