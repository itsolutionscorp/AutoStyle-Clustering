# Each allergy has a score of 2**(list iterator)
AllergyList = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

class Allergies:
	def __init__(self, score):
		self.score = score
		self.list = []
		for i in range(0,len(AllergyList)):
			# If the bitwise compare of the score and each allergy isn't 0
			if int(self.score) & 2**i:
				self.list.append(AllergyList[i])

	def is_allergic_to(self, allergy):
		return allergy in self.list
