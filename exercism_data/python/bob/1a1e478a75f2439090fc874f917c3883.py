def hey(s):
	if s.isupper():
		return 'Whoa, chill out!'
	if s[-1:]=='?':
		return 'Sure.'
	if s.split()==[]:
		return 'Fine. Be that way!'
	return "Whatever."
