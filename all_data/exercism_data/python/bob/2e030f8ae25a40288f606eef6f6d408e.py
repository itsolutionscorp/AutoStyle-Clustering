class Bob():

	def hey(self, input):
		
		silence =  not input
		shouting = input.isupper()
		question = input.endswith('?')

		return 'Fine. Be that way.' if silence else \
		       'Woah, chill out!' if shouting else \
		       'Sure.' if question else \
		       'Whatever.'
