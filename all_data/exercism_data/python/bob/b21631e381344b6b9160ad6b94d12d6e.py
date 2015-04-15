class Bob(object):
	def hey(self, statement):
		if statement == '':
			return 'Fine. Be that way.'
		elif statement[-1] == '?':
			return 'Sure.'
		elif statement.isupper():
			return 'Woah, chill out!'
		else:
			return 'Whatever.'
