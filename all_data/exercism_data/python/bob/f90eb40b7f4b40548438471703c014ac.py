def hey(raw_input):
	text = raw_input.strip()
	if not text:
		return 'Fine. Be that way!'
	elif text.isupper():
		return 'Woah, chill out!'
	elif text[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
