class Bob(object):

	def hey(self, string):
		if string is None or string.strip() == '':
			return 'Fine. Be that way!'
		elif (string.isupper()):
			return 'Woah, chill out!'
		elif (string.endswith("?")):
			return 'Sure.'
		else:
			return 'Whatever.'
