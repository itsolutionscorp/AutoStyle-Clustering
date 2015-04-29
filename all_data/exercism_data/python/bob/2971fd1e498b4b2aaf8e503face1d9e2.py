class Bob:

	def hey(self, message):
		if message.strip() == '':
			return "Fine. Be that way!"
		elif message.isupper():
			return "Woah, chill out!"
		elif message[-1] == '?':
			return "Sure."
		else:
			return "Whatever."
