# This can be added to or removed from, as long as the keys, or scores,  are still powers of 2
AllergyScores = {
		1:'eggs',
		2:'peanuts',
		4:'shellfish',
		8:'strawberries',
		16:'tomatoes',
		32:'chocolate',
		64:'pollen',
		128:'cats',
		}

class Allergies:
	def __init__(self, score):
		self.score = score
		self.list = []
		# sorted() ensures that the resulting self.list is ordered correctly
		for allergyScore in sorted(AllergyScores.keys()):
			if int(self.score) & int(allergyScore):
				self.list.append(AllergyScores[allergyScore])

	def is_allergic_to(self, allergy):
		return allergy in self.list
