class Bob:
	def hey(self, msg):
		if not msg or msg.strip() == '':
			return 'Fine. Be that way!'
		if msg.isupper():
			return 'Woah, chill out!'
		if msg.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
