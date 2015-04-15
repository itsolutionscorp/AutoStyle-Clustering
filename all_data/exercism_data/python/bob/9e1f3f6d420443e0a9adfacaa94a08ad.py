import re

class bob:

	def hey(msg):

		if msg.strip():
			if msg.isupper():
				return 'Whoa, chill out!'
			elif msg.endswith('?'):
				return 'Sure.'
			else:
				return 'Whatever.'
		else:
			return 'Fine. Be that way!'
