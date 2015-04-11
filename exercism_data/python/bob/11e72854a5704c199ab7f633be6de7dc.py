class Bob():

	def hey(self, salutation):

		if salutation and salutation.isspace():
			return "Fine. Be that way!"

		all_caps = salutation.isupper()		

		if all_caps:
			return "Woah, chill out!"
		elif len(salutation) > 0 and salutation[-1] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
