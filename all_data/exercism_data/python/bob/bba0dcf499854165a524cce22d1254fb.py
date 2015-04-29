def hey(s):
	if s == s.upper() and s != s.lower():
		return 'Whoa, chill out!'
	if len(s) > 0 and s[-1] == '?':
		return 'Sure.'
	if s.strip() == '':
		return 'Fine. Be that way!'
	return 'Whatever.'
