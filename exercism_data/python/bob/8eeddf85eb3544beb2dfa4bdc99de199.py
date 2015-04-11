import re

class Bob(object):
	"""A lackadasical teenager."""

	no_lowercase = re.compile('^[^a-z]*$')
	question = re.compile('\?$')

	def __init__(self):
		pass

	def hey(self, message):
		if message == None or len(message.strip()) == 0:
			return 'Fine. Be that way!'
		elif Bob.no_lowercase.search(message):
			return 'Woah, chill out!'
		elif Bob.question.search(message):
			return 'Sure.'
		else:
			return 'Whatever.'
