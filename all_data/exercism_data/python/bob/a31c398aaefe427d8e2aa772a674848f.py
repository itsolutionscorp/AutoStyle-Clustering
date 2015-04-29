class Bob():

	def hey(self, salutation):

		if not salutation or salutation.isspace():
			return "Fine. Be that way!"

		all_caps = salutation.isupper()		

		if all_caps:
			return "Woah, chill out!"
		elif len(salutation) > 0 and salutation.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
