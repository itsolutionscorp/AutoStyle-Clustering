class Bob:
	def Bob(self):
		return self

	def isNothing(self, sentence):
		#TYPE 1:
		#sentence.replace(" ","")
		#TYPE 2:
		#sentence = ' hello  apple'
		#" ".join(sentence.split())
		#TYPE 3:
		#sentence.strip()
		#TYPE 4:
		return len(sentence) == 0 or sentence.isspace()
		
	def isQuestion(self, sentence):
		return sentence[len(sentence)-1] == '?'

	def isMayus(self, sentence):
		upper_sentence = sentence.upper()
		lower_sentence = sentence.lower()
		return (upper_sentence == sentence) and (lower_sentence != sentence)

	def hey(self, sentence):
		if isinstance(sentence, unicode) == True:
			sentence.encode('ascii', 'ignore')

		if self.isMayus(sentence) == True:
			return 'Woah, chill out!'
		elif self.isNothing(sentence) == True:
			return 'Fine. Be that way!'
		elif self.isQuestion(sentence) == True:
			return 'Sure.'
		return 'Whatever.'
