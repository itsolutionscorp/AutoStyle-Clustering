class Bob:
	def hey(self, input):
		input = input.strip()
		if len(input) == 0 :
			return "Fine. Be that way!"
		if (input.isupper() == True):
			return "Woah, chill out!"
		if input.endswith('?'):
			return "Sure."
		return "Whatever."
