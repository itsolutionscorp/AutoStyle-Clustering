class Bob():
	
	def Bob():
		return self

	def hey(self, question):
		if(not question or question.isspace()):
			return 'Fine. Be that way!'
		elif(question.isupper()):
			return 'Woah, chill out!'
		elif(question[-1] == '?'):
			return 'Sure.'
		else:
			return 'Whatever.'
