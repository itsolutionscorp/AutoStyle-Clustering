def hey(exclamation):
	if exclamation.strip(" \t") == '':
		return 'Fine. Be that way!'
	elif exclamation == exclamation.upper() and exclamation != exclamation.lower():
		return 'Woah, chill out!'
	elif exclamation[-1] == "?":
		return 'Sure.'
	return "Whatever."
