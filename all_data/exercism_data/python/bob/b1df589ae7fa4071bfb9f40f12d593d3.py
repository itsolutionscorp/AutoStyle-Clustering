def hey(s):
	if not s.isspace() and s.upper() == s and s.swapcase() != s:
		return 'Woah, chill out!'
	elif s.endswith('?'):
		return 'Sure.'
	elif s.isspace() or s == '':
		return 'Fine. Be that way!'
	else:
		return "Whatever."
