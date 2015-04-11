class Bob:
	def hey(self, message):
		if not message or message.isspace():
			return 'Fine. Be that way!'

		if message == message.upper():
			return 'Woah, chill out!'

		if message.endswith('?'):
			return 'Sure.'

		return 'Whatever.'
