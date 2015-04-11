class Bob():

	def hey(self, text):
		if self.is_silence(text):
			return 'Fine. Be that way.'
		if self.is_shouting(text):
			return 'Woah, chill out!'
		if self.is_question(text):
			return 'Sure.'	
		return 'Whatever.'

	def is_silence(self, text):
		return not text

	def is_shouting(self, text):
		return text.isupper()

	def is_question(self, text):
		return text[-1] == '?'
