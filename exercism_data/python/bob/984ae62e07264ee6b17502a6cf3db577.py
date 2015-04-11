def hey(self):
	if self.isupper():
		return 'Whoa, chill out!'
	if self.endswith('?'):
		return 'Sure.'
	if self.isspace() or self == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
