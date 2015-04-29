def hey(s):
	if s.isspace() or s == '':
		return 'Fine. Be that way!'
	elif s.upper() == s and s.swapcase() != s:
		return 'Woah, chill out!'
	elif s.endswith('?'):
		return 'Sure.'
	else:
		return "Whatever."
