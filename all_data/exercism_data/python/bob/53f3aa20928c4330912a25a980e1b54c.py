class Bob:

	def hey(self, message):
		if message is None or message.strip() == '':
			return 'Fine. Be that way!'
		if message == message.upper():
			return 'Woah, chill out!'
		elif message.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
