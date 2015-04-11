class Bob():
	
	def hey(self, text):
		if len(text) == 0:
			return 'Fine. Be that way.'
		elif text == 'A statement.':
			return 'Whatever.'
		elif text.isupper():
			return 'Woah, chill out!'
		else:
			return 'Sure.'
