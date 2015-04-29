class Bob:
	def hey(self, s):
		if s is None or s.strip() == '':
			return 'Fine. Be that way!'
		if s.upper() == s:
			return 'Woah, chill out!'
		if s.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
