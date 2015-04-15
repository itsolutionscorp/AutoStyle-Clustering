def enum(**enums):
	return type('Enum', (), enums)

	
class Phrase:
	Is = enum(Silence = 0, Shouting = 1, Question = 2, Other = 3)
	
	def __init__(self, phrase):
		self.phrase = phrase
		
	def type(self):
		if (self.phrase == None) or (self.phrase.strip() == ''):
			return Phrase.Is.Silence
		elif (self.phrase == self.phrase.upper()):
			return Phrase.Is.Shouting
		elif self.phrase.endswith('?'):
			return Phrase.Is.Question
		else:
			return Phrase.Is.Other

    
class Bob:
	Says_to = { Phrase.Is.Silence : 'Fine. Be that way.',
		  	    Phrase.Is.Shouting: 'Woah, chill out!',
			    Phrase.Is.Question: 'Sure.',
			    Phrase.Is.Other   : 'Whatever.' }

	def hey(self, s):
		return Bob.Says_to[Phrase(s).type()]
