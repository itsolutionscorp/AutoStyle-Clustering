class Bob(object):
	def hey(self, msg):
		if msg is None or not msg.strip():
			return 'Fine. Be that way!'
		elif msg.isupper(): 
			return 'Woah, chill out!'
		elif msg.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
