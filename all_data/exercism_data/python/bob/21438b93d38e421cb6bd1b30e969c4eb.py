
class Bob(object):
	def __init__(self):
		pass

	def hey(self, message):
		if message.isupper(): # Am I being yelled at?
			return "Woah, chill out!"

		elif message[-1:] == "?": # Am I being asked a question?
			return "Sure."

		elif message == '' or message == len(message) * message[0]: # Is this message empty?
			return "Fine. Be that way!"
			
		else:
			return "Whatever."
