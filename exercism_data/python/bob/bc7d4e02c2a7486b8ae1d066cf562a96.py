class Bob(object):
	def hey(self, question):
		if not question or question.isspace():
			return 'Fine. Be that way!'
		elif question.isupper():
			return 'Woah, chill out!'
		elif question.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'