class Bob():

	def hey(self, text):
		if self.__is_silence(text):
			return 'Fine. Be that way.'
		if self.__is_shouting(text):
			return 'Woah, chill out!'
		if self.__is_question(text):
			return 'Sure.'	
		return 'Whatever.'

	def __is_silence(self, text):
		return not text

	def __is_shouting(self, text):
		return text.isupper()

	def __is_question(self, text):
		return text[-1] == '?'
