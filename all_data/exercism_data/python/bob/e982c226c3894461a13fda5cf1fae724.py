class Bob:
	def hey(self, message = ""):
		if not message.strip():
			return "Fine. Be that way!"
		elif message.upper() == message and message.lower() != message:
			return "Woah, chill out!"
		elif message[-1:] == "?":
			return "Sure."
		else:
			return "Whatever."
