class Bob():

	def hey(self, salutation):
		if not salutation or salutation.isspace():
			return "Fine. Be that way!"		
		elif salutation.isupper():
			return "Woah, chill out!"
		elif len(salutation) > 0 and salutation.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
