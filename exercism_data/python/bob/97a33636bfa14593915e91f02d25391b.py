import string

class Bob:
	def hey(self, message):
		result = "Whatever."

		if (message.strip() == ''):
			result = "Fine. Be that way!"
		elif (message.isupper()):
			result = "Woah, chill out!"
		elif (message.endswith('?')):
			result = "Sure."

		return result
