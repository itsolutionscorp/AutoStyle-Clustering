class Bob(object):
	def hey(self, msg):
		response = 'Whatever.'
		if msg is None or not msg.strip():
			response = 'Fine. Be that way!'
		elif all(char.isupper() for char in msg if char.isalpha()): 
			response = 'Woah, chill out!'
		elif msg.endswith('?'):
			response = 'Sure.'
		return response
