class Bob():
		
	def hey(self, mes):
		
		if not mes or mes.isspace():
			return self.silence()			
		elif mes.isupper() or mes.isupper() and mes.endswith('!'):
			return self.shout()
		elif mes.endswith('?'):
			return self.question()
		else:
			return self.normal()
			
	def shout(self):
		return 'Woah, chill out!'
		
	def question(self):
		return 'Sure.'

	def normal(self):
		return 'Whatever.'

	def silence(self):
		return'Fine. Be that way!'
		
