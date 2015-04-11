import string

class Word:
	LETTERS1 = "AEIOULNRST"
	LETTERS2 = "DG"
	LETTERS3 = "BCMP"
	LETTERS4 = "FHVXY"
	LETTERS5 = "K"
	LETTERS6 = "JX"
	LETTERS7 = "QZ"
	VALUES = dict(zip(LETTERS1, [1]*len(LETTERS1))+
			   zip(LETTERS2, [2]*len(LETTERS2))+
			   zip(LETTERS3, [3]*len(LETTERS3))+
			   zip(LETTERS4, [4]*len(LETTERS4))+
			   zip(LETTERS5, [5]*len(LETTERS5))+
			   zip(LETTERS6, [8]*len(LETTERS6))+
			   zip(LETTERS7, [10]*len(LETTERS7)))
	
	def __init__(self, word):
		self.word = word.translate(None, string.punctuation).strip().upper()
	
	def score(self):
		return sum([self.VALUES[l] for l in self.word])
