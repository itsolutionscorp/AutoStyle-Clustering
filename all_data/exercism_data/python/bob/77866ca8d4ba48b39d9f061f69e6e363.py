class Bob():

	def hey(self, text):
		if not text:
			return 'Fine. Be that way.'
		elif text.isupper():
			return 'Woah, chill out!'
		elif text[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
