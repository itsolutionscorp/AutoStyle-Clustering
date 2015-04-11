def hey(sentence):
	if sentence.isupper():
		return 'Whoa, chill out!'
	elif sentence.isspace() or (not sentence) :
		return 'Fine. Be that way!'
	elif sentence[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
