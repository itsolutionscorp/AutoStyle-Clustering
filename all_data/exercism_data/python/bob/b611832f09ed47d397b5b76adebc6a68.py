class Bob:
	def hey(self, words):

		def yelling():
			return words.isupper()

		def question():
			return words.endswith('?')
			
		def nothing():
			return not words or words.isspace()

		if nothing():
			return 'Fine. Be that way.'
		elif yelling():
			return 'Woah, chill out!'
		elif question():
			return 'Sure.'
		else:
			return 'Whatever.'
