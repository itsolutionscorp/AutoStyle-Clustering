def hey(exclamation):
	if not exclamation.strip(" \t"):
		return 'Fine. Be that way!'
	elif exclamation.isupper():
		return 'Woah, chill out!'
	elif exclamation.endswith("?"):
		return 'Sure.'
	return "Whatever."
