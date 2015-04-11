class Bob(object):
	
	def hey(self, message):
		if message.isupper():
			return "Whoa, chill out!"
		elif message.endswith('?'):
			return "Sure."
		elif message is None or message.strip() == "":
			return "Fine. Be that way!"
		else:
			return "Whatever."
