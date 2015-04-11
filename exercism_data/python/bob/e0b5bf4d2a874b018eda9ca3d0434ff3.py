def hey(s):
	if  ' '+s.isspace():
		return "Fine. Be that way!"
	if s.isupper() and s.upper() != s.lower():
		return 'Whoa, chill out!'
	if s.endswith('?'):
		return 'Sure.'
	else: 
		return 'Whatever.'
