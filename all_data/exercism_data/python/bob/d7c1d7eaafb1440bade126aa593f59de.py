class Bob:
	def hey(self, str):
		if str.strip() == '':
			return 'Fine. Be that way!'
		if not str.islower() and str.isupper():
			return 'Woah, chill out!'
		if str.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
