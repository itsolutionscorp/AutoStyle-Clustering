class Bob():
	def hey(self, input):
		
		if input == '':
			return 'Fine. Be that way.'

		if input.isupper():
			return 'Woah, chill out!'

		if input[-1] == '?':
			return 'Sure.'

		return "Whatever."
