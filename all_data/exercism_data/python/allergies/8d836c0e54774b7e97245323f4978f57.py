# Allergy info

from math import log

class Allergies(object):

	def __init__(self, allergy_score):
		allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
		self.list = []
		while (allergy_score > 0):
			score = round_pow2(allergy_score)
			allergen_index = int(log(score, 2))
			if allergen_index < len(allergens):
				self.list = [allergens[allergen_index]] + self.list
			allergy_score = allergy_score - score

	def is_allergic_to(self, allergen):
		return allergen in self.list

def round_pow2(num):
	pow2_num = max(num-1, 0)

	pow2_num |= pow2_num >> 1
	pow2_num |= pow2_num >> 2
	pow2_num |= pow2_num >> 4
	pow2_num |= pow2_num >> 8
	pow2_num |= pow2_num >> 16
	pow2_num = pow2_num + 1

	return pow2_num if pow2_num <= num else pow2_num / 2
