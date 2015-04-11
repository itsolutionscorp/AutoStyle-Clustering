class Bob(object):
	def hey(self, speech):
		if speech.strip():
			if speech.isupper():
				return "Woah, chill out!"
			if speech.endswith("?"):
				return "Sure."
		else:
			return "Fine. Be that way!"
		return "Whatever."
