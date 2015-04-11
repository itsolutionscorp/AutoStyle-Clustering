class Bob:
	def hey(self, msg):
		if msg.isupper():
			return 'Woah, chill out!'
		elif msg.endswith('?'):
			return 'Sure.'
		elif msg.strip() == '':
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
