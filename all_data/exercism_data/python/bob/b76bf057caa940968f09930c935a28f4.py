class Bob(object):

	def hey(self, message):
		if message == None or message.strip() == '':
			return 'Fine. Be that way!'
		elif message.upper() in message:
			return 'Woah, chill out!'
		elif message[-1:] == '?':
			return 'Sure.'
		return 'Whatever.'
