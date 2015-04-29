# Note I was not able to figure out how to handle Umlauts. Please suggest some clues!
class Bob(object):
	# The hey method that takes in a string input
	def hey(self, msg):
		# Handle cases where string is empty or has no alphanumeric content
		if not msg or msg is None or msg.isspace():
			return 'Fine. Be that way!'
		# Handle cases where string has all uppercase chars
		elif msg.isupper():
			return 'Woah, chill out!'
		# Handle cases where string has a question mark at the end
		elif msg[-1] == '?':
			return 'Sure.'
		# Handle other cases
		else:
			return 'Whatever.'
