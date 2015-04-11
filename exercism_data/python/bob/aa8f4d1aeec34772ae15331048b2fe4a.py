class Bob:
	def is_yell(self, text):
		return text.isupper()

	def is_question(self, text):
		return text.endswith('?')

	def is_silence(self, text):
		return text == "" or text.isspace()

	def hey(self,text):
		if self.is_yell(text):
			return 'Woah, chill out!'
		elif self.is_question(text):
			return 'Sure.'
		elif self.is_silence(text):
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
