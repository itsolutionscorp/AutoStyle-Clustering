class Bob(object):
	def hey(self, statement):
		if self._is_yell(statement):
			return 'Woah, chill out!'
		elif self._is_question(statement):
			return 'Sure.'
		elif self._is_silence(statement):
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'

	def _is_yell(self, statement):
		return statement.isupper()

	def _is_question(self, statement):
		return statement.endswith('?')

	def _is_silence(self, statement):
		return statement == "" or statement.isspace()
