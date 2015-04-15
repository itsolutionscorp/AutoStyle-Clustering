def hey(s):
	if s.endswith('?'):
		if not s.isspace() and s.upper() == s and s.swapcase() != s:
			return 'Woah, chill out!'
		else:
			return 'Sure.'
	elif s.isspace() or s == '':
		return 'Fine. Be that way!'
	elif not s.isspace() and s.upper() == s and s.swapcase() != s:
		return 'Woah, chill out!'
	else:
		return "Whatever."
