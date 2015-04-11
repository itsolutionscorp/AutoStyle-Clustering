class Bob():

	def hey(self, text):
		if self._is_silence(text):
			return 'Fine. Be that way.'
		if self._is_shouting(text):
			return 'Woah, chill out!'
		if self._is_question(text):
			return 'Sure.'	
		return 'Whatever.'

	def _is_silence(self, text):
		return not text

	def _is_shouting(self, text):
		return text.isupper()

	def _is_question(self, text):
		return text[-1] == '?'
