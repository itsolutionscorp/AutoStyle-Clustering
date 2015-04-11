def hey(self):
	if self.isupper():
		return 'Whoa, chill out!'
	elif self.endswith('?'):
		return 'Sure.'
	elif self.isspace() or self == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
