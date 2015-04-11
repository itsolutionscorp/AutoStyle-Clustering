import re

class Bob(object):

	def hey(self, question):
		response = 'Whatever.'

		if question.endswith('?'):
			response = 'Sure.'

		if re.search('[A-Z]', question) and question == question.upper():
			response = 'Woah, chill out!'

		if len(question.strip()) == 0:
			response = 'Fine. Be that way!'

		return response
