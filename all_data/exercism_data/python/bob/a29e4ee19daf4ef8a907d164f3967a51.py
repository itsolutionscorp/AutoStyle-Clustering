class Bob:
	def __init__(self):
		pass
	
	def hey(self, message):
		message = unicode(message)
		if not message:
			reply = 'Fine. Be that way!'
		elif message.isspace():
			reply = 'Fine. Be that way!'
		elif message.isupper():
			reply = 'Woah, chill out!'
		elif message.endswith('?'):
			reply = 'Sure.'
		else:
			reply = 'Whatever.'


		return reply
