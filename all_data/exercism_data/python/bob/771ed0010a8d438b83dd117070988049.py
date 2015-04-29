class Bob(object):
	def hey(self,prompt):
		yelling = True
		question = False
		nothing = True
		noalpha = True
		for char in prompt:
			if char.isalpha():
				noalpha = False
			if char != ' ':
				nothing = False
			if char.islower():
				yelling = False
		if len(prompt) != 0 and prompt[-1] == '?':
			question = True


		if nothing:
			return 'Fine. Be that way!'
		if not noalpha and yelling:
			return 'Woah, chill out!'
		if question:
			return 'Sure.'
		return 'Whatever.'
