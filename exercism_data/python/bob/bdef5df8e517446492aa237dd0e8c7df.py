import re

class Bob(object):
	
	def hey(self, msg):
		if msg is None or len(msg.strip()) == 0:
			return 'Fine. Be that way!'
		elif re.search('[a-z]', msg) is None:
			return 'Woah, chill out!'
		elif re.match('^.*\?$', msg):
			return 'Sure.'
		return 'Whatever.'
