#!/usr/bin/python
# CLASS Teennager to manage lackadaisical teenager responses to questions

class teenager(object):
	# init function
	def __init__(self, text):
		# response attribytes
		self.question = "Sure."
		self.yell = "Whoa, chill out!"
		self.nothing = "Fine. Be that way!"
		self.anythingElse = "Whatever."
		
		# init text sent in
		self.text = text
	
	# Respond to any non defined text
	def allElse(self):
		return self.anythingElse
			
	# Check it text is a quesions
	def askQuestion(self):
		if (self.text[-1:] == '?'):
			return self.question
		else:
			return self.allElse()
	
	# Check if text is yelling
	def yelling(self):
		if (self.text.isupper()):
			return self.yell
		else:
			return self.askQuestion()
			
	# Check if text is blank/NULL or has only special characters and white speaces
	def blank(self):
		if (self.text.strip() == ""):
			return self.nothing
		else:
			return self.yelling()
		
	# Parse quesion for response
	def determineResponse(self):
		return self.blank()
		
		
# Function call from Test suite		
def hey(text):		
	b = teenager(text)
	return b.determineResponse()
		
