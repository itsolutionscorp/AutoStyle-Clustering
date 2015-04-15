class Bob ():
	def __init__(self):
		pass

	def hey(self, msg):
		if msg is None or msg.strip() == '':
			return 'Fine. Be that way.'
		elif msg.upper() == msg:
			return 'Woah, chill out!'
		elif msg.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
