class Sentence(object):
	
	def __init__(self, sentence):
		self.sentence = sentence

	def is_silent(self):
		if not self.sentence:
			return True
		return self.sentence.strip() == ''
	
	def is_shouting(self):
		return self.sentence.isupper()
	
	def is_question(self):
		return self.sentence.strip()[-1] == '?'

class Bob(object):
	
	def hey(self, sentence):
		sentence = Sentence(sentence)
		if sentence.is_silent():
			return 'Fine. Be that way!'
		if sentence.is_shouting():
			return 'Woah, chill out!'
		if sentence.is_question():
			return 'Sure.'
		return 'Whatever.'			
