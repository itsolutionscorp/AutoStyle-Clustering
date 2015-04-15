class PhraseType:
	Silence  = 0
	Shouting = 1
	Question = 2
	Other    = 3
	
	@classmethod
	def classify(cls, phrase):
		if (phrase == None) or (phrase.strip() == ''):
			return cls.Silence
		elif (phrase == phrase.upper()):
			return cls.Shouting
		elif phrase.endswith('?'):
			return cls.Question
		else:
			return cls.Other

class Bob:
	Responses = { PhraseType.Silence : 'Fine. Be that way.',
	  	  	      PhraseType.Shouting: 'Woah, chill out!',
			      PhraseType.Question: 'Sure.',
			      PhraseType.Other   : 'Whatever.' }

	def hey(self, s):
		return Bob.Responses[PhraseType.classify(s)]
