#message = raw_input("--> ")

# Simple Bob Responses

# Question asked
class Bob:
	#def __init__(self):
	def hey(self, message):
		if message.endswith('?'):
			return "Sure."
		# Empty message
		elif len(message) == 0:
			return "Fine. Be that way."
		# All caps
		elif message.isupper():
			return 'Woah, chill out!'
		# Everything else
		else:
			return "Whatever."
