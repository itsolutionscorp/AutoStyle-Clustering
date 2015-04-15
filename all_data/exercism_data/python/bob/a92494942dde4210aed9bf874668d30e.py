def hey(text):
	if text.upper() == text and not text.lower() == text: # Is all caps
		return 'Whoa, chill out!'
	elif not text.strip(): # Empty message
		return 'Fine. Be that way!'
	elif text[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
