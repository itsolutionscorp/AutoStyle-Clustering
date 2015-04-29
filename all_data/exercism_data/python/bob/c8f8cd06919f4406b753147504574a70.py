class Bob():

	def hey(self, inp):

		if inp is None or len(inp.split()) == 0:
			return "Fine. Be that way!"

		allCaps = True
		containsAlpha = False

		for c in inp:
			if c.isalpha():
				containsAlpha = True
				if not c.isupper():
					allCaps = False

		if allCaps and containsAlpha:
			return "Woah, chill out!"
		elif len(inp) > 0 and inp[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
