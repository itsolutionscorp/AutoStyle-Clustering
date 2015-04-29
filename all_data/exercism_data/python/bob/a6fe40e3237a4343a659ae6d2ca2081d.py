
def hey(sentence):
	sentence = Interpreter(sentence)
	if sentence.IsSilent():
		return "Fine. Be that way!"
	if sentence.IsYelling() and sentence.ContainsLetter():
		return "Whoa, chill out!"
	elif sentence.IsQuestion():
		return "Sure."
	else:
		return "Whatever."
		
		
class Interpreter:
	def __init__(self, sentence):
		self.Satz = sentence		
		
	def IsQuestion(self):
		return self.Satz.endswith("?")				
		
	def IsYelling(self):
		return (self.Satz == self.Satz.upper() )
		
	def IsSilent(self):
		return (self.Satz == "") or (self.Satz.isspace())	
	
	def ContainsLetter(self):
	
		Contains = False
		for I in self.Satz:
			if I.isalpha():
				Contains = True
		return Contains
