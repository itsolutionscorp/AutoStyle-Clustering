class Bob(object):

	def hey(self, statement):
		if self.is_empty(statement):
			return 'Fine. Be that way.'
		elif self.is_question(statement):
			return 'Sure.'
		elif self.is_shouting(statement):
			return 'Woah, chill out!'
		else:
			return 'Whatever.'
			
	def is_empty(self, statement):
		return statement == ''
		
	def is_question(self, statement):
		return statement.endswith('?')
		
	def is_shouting(self, statement):
		return statement.isupper()
