class Bob:
	def hey(self, words):
		statement = Statement(words)
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
		return self.words.endswith('?')
	
	def is_shouting(self):
		return self.words.isupper()
		
	def is_silence(self):
		return self.words.strip() == ''
