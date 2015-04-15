import re

class bob:

	def hey(msg):

		if re.search('^\s*$', msg):
			return 'Fine. Be that way!'
		elif re.search('\?$', msg):
			return 'Sure.'
		elif re.search('[A-Z]+[\W]?[0-9]*[\!\?]?$', msg):
			return 'Whoa, chill out!'
		else:
			return 'Whatever.'
