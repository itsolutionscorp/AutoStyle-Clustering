def hey(s):
	if (s == '    \t' or s == ''):
		return "Fine. Be that way!"
	if (s.upper() == s and s.upper() != s.lower()):
		return 'Whoa, chill out!'
	if (s[-1] == '?'):
		return 'Sure.'
	else: 
		return 'Whatever.'
