class Bob(object):
	def hey(self, input_):
		"""address Bob"""
		
		if isinstance(input_, basestring): #spaces at the start and end of the input are meaningless and cause silence to look like shouting
			input_ = input_.strip()
			
		if not input_: #Silent input
			return "Fine. Be that way!"
		
		if input_.upper() == input_: #there's some shouting going on!
			return "Woah, chill out!"
		
		if input_.endswith("?"): #If the input is a question
			return "Sure."
		
		return "Whatever."
