class Bob():

	def hey(self, input):

	 	def is_silence(x):
			return not x

		def is_shouting(x):
			return x.isupper()

		def is_question(x):
			return x.endswith('?')

		if is_silence(input):
			return 'Fine. Be that way.'
		elif is_shouting(input):
			return 'Woah, chill out!'
		elif is_question(input):
			return 'Sure.'
		else:
			return 'Whatever.'
