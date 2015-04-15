def hey(sentence):
	sentence = sentence.strip();
	if sentence.isupper():
		return 'Woah, chill out!'
	elif sentence.endswith('?'):
		return 'Sure.'
	elif len(sentence) == 0:
		return 'Fine. Be that way!'
	return 'Whatever.'
