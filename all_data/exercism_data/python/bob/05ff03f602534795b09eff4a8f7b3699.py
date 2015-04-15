def hey(s):
	if len(s.strip()) == 0:
		return 'Fine. Be that way!'
	if s.isupper():
		return 'Whoa, chill out!'
	if s[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
