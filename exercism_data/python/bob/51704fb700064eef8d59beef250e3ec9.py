def hey(s):
	if  s.strip() == '':
		return "Fine. Be that way!"
	if s.upper() == s and s.upper() != s.lower():
		return 'Whoa, chill out!'
	if s.endswith('?'):
		return 'Sure.'
	else: 
		return 'Whatever.'