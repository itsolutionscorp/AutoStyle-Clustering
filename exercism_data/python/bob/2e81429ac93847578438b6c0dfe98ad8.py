

def hey(words):
	if words.isupper():
		return 'Woah, chill out!'
	elif words.isspace() or len(words) == 0:
		return 'Fine. Be that way!'
	elif words[len(words) - 1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
