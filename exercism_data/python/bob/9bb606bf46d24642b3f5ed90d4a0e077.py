import re

#print u"\xdcML\xe4\xdcTS!"
#print u"\xdcML\xc4\xdcTS!"

class Bob():
	def hey(self, str):

		# Say nothing
		if not re.match('\S+', str):
			return "Fine. Be that way!"

		# Shout
		if re.search('[A-Z]', str, re.U) and not re.search('[a-z]', str, re.U):
			return 'Woah, chill out!'

		# Ask question
		if str[-1] == '?':
			return 'Sure.'

		return "Whatever."
