def hey(text):
	if (len(text) == 0 or (len(text)-len(text.lstrip()) == len(text))):
		return 'Fine. Be that way!'
	elif (text.isupper()):
		return 'Woah, chill out!'
	elif (text[len(text)-1] == '?'):
		return 'Sure.'
	else:
		return 'Whatever.'
