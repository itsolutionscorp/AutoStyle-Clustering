class Bob ():

	def hey(self, msg):
		if self.is_empty(msg):
			return 'Fine. Be that way.'
		elif msg.isupper():
			return 'Woah, chill out!'
		elif msg.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'

	def is_empty(self, msg):
		return msg is None or msg.strip() == ''
