class Allergies(object):

	def __init__(self, score):
		# adjust for scores that are too high
		while score > 255:
			score -= 256
		# create default empty list
		self.list = []
		# convert score to 8-bit binary to represent 8 allergens
		binScore = bin(score)[2:].zfill(8)
		# list of allergens
		allergens = ['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']
		for allergen in reversed(allergens):
			# if score denotes allergy, add it to the list
			if binScore[allergens.index(allergen)] == '1':
				self.list.append(allergen)

	def is_allergic_to(self, allergen):
		if allergen in self.list:
			return True
		return False
