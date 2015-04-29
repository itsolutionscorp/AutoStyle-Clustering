import re

class Sentence:
	
	def __init__(self, sentence):

		self.sentence = sentence

	def is_silent(self):
		if not self.sentence:
			return True
		return self.sentence.strip() == ''
	
	def is_shouting(self):
		return re.search('[abcdefghijklmnopqrstuvwxyz]', self.sentence) == None
	
	def is_question(self):
		return self.sentence.strip()[-1] == '?'

class Bob:
	
	def hey(self, sentence):
		
		sentence = Sentence(sentence)
		
		if sentence.is_silent():
			return 'Fine. Be that way!'
		
		if sentence.is_shouting():
			return 'Woah, chill out!'

		if sentence.is_question():
			return 'Sure.'

		return 'Whatever.'			
