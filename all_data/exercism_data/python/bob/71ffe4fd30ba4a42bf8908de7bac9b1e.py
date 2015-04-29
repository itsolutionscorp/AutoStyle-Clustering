def hey(text):
	if text.isupper():
		return 'Whoa, chill out!'
	elif not text.strip(): # Empty message
		return 'Fine. Be that way!'
	elif text.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
