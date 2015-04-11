import sys, re, string

class Bob:
	def hey(self, message):
		message = message.strip()
		textOnly = message.strip(string.punctuation + string.digits + string.whitespace)
		if (len(message) == 0):
			return "Fine. Be that way!"
		elif (textOnly.upper() == textOnly and len(textOnly) > 0):
			return "Woah, chill out!"
		elif (message.endswith('?')):
			return "Sure."
		return "Whatever."
