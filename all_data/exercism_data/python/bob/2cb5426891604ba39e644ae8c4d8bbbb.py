import re

class Bob:
	def hey(self, words):
		return self.response(Statement(words))
		
	def response(self, statement):
		if statement.is_question():
			return 'Sure.'
		elif statement.is_silence():
			return 'Fine. Be that way.'
		elif statement.is_shouting():
			return 'Woah, chill out!'
		else:
			return "Whatever."
		
class Statement:
	def __init__(self, words):
		self.words = words
		
	def is_question(self):
		if len(self.words.split('?')) > 1:
			return True
		else:
			return False
	
	def is_shouting(self):
		return self.words.upper() == self.words
		
	def is_silence(self):
		return self.words.strip() == ''
