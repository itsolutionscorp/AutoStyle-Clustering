class Bob(object):
	def hey(self, statement):
		if self.isEmpty(statement):
			return 'Fine. Be that way.'
		elif self.isQuestion(statement):
			return 'Sure.'
		elif self.isShouting(statement):
			return 'Woah, chill out!'
		else:
			return 'Whatever.'
	def isEmpty(self, statement):
		return statement == ''
	def isQuestion(self, statement):
		return statement[-1] == '?'
	def isShouting(self, statement):
		return statement.isupper()
