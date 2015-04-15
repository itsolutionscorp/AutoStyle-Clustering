#!/usr/bin/python
# CLASS Teennager to manage lackadaisical teenager responses to questions
import re, string

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
		
	# Check if text is yelling
	def yelling(self):
		# remove punctuation and digits
		exclude = set(string.punctuation)
		no_digits = ''.join([i for i in self.text if not i.isdigit()])
		no_punctuation = ''.join(ch for ch in no_digits if ch not in exclude)
		
		# check if upper case
		if (no_punctuation == no_punctuation.upper()):
			
			# check that string wasn't only punctuation
			if (no_punctuation.strip() != ""):
				return self.yell
			else:
				return self.allElse()
		else:
			return self.allElse()
			
	# Check it text is a quesions
	def askQuestion(self):
		# check if quesion mark is last character
		if (self.text[-1:] == '?'):
			# check for digits
			if (self.text[:len(self.text)-1].isdigit() == True):
				return self.question
			# check for upper case stuff
			elif (self.text != self.text.upper()):
				return self.question
			else:
				return self.yelling()
		else:
			return self.yelling()
			
	# Check if text is blank/NULL or has only special characters and white speaces
	def blank(self):
		if (self.text == ""):
			return self.nothing
		elif (self.text == ''):
			return self.nothing 
		elif (self.text.strip(' \t\n\r') == ''):
			return self.nothing
		else:
			return self.askQuestion()
		
	# Parse quesion for response
	def determineResponse(self):
		return self.blank()
		
		
# Function call from Test suite		
def hey(text):		
	b = teenager(text)
	return b.determineResponse()
		
