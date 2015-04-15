class Bob():

	def is_silence(self, x):
		return not x

	def is_shouting(self, x):
		return x.isupper()

	def is_question(self, x):
		return x.endswith('?')

	def hey(self, input):

		if self.is_silence(input):
			return 'Fine. Be that way.'
		elif self.is_shouting(input):
			return 'Woah, chill out!'
		elif self.is_question(input):
			return 'Sure.'
		else:
			return 'Whatever.'
