import string

class Word(object):

	points_ref = {"aeioulnrst": 1, "dg": 2, "bcmp": 3, "fhvwy": 4, "k": 5, "jx": 8, "qz": 10}

	def __init__(self, word):
		self.word = word.rstrip().lower()

	def score(self):
		score = 0

		for letter in self.word:
			for key in self.points_ref.keys():
				if letter in key:
					score += self.points_ref[key]

		return score
