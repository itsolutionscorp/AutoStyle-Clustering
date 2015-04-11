"Demo"
class Bob(object):
	'Bob is a chatbot. Talk to him by sending a "hey" message.'
	def hey(self, message):
		'Returns a response string'
		if not message or not message.strip():
			return 'Fine. Be that way!'
		if message.isupper():
			return 'Woah, chill out!'
		if message.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
