def hey(sentence):
	if sentence.isupper():
		return 'Woah, chill out!'
	elif sentence.endswith('?'):
		return 'Sure.'
	elif sentence.isspace() or len(sentence) == 0:
		return 'Fine. Be that way!'
	return 'Whatever.'
