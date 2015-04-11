"Demo"
class Bob(object):
	'Bob is a chatbot. Talk to him by sending a "hey" message.'
	def hey(self, message):
		'Returns a response string'
		if not message or not message.strip():
			return 'Fine. Be that way!'
		if message.upper() == message:
			return 'Woah, chill out!'
		if message[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
