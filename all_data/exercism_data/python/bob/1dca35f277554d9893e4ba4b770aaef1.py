import re

class Bob:
	def hey(self, input):

		if input.upper() == input and re.match('.*[a-zA-Z]+', input) :
			return 'Woah, chill out!'

		elif re.match('.*\?$', input) is not None :
			return 'Sure.'

		elif re.match('^$|\s+', input):
			return 'Fine. Be that way!'

		else :
			return 'Whatever.'
