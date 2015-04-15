class Bob:
	def hey(self, msg):
		if msg.isupper():
			return 'Woah, chill out!'
		if msg.endswith('?'):
			return 'Sure.'
		if not (msg and msg.strip()):
			return 'Fine. Be that way!'
		return 'Whatever.'
