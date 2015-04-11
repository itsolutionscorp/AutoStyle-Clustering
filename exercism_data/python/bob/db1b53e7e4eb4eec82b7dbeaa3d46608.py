class Bob(object):
	def hey(self, question):
		if None == question or question.isspace() or '' == question:
			return 'Fine. Be that way!'
		elif question.isupper():
			return 'Woah, chill out!'
		elif question.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
