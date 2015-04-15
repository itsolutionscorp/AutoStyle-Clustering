import re

class Bob():
	def hey(self, str):

		# Say nothing
		if not re.match('\S+', str):
			return "Fine. Be that way!"

		# Shout
		if str.isupper():
			return 'Woah, chill out!'

		# Ask question
		if str.endswith('?'):
			return 'Sure.'

		return "Whatever."
