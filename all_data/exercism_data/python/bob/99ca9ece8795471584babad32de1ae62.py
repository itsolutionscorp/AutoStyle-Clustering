class Bob:

	def hey(self, message):
		if message is None or len(message.strip()) == 0:
			return 'Fine. Be that way!'
                # Convert from a string object to a unicode object
                # for correct umlaut matching
		message = message.decode('latin-1')
		if message.isupper():
			return 'Woah, chill out!'
		if message.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
