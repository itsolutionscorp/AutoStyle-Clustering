# Allergy info

from math import ceil, log

class Allergies(object):
	list = []

	def __init__(self, allergy_score):
		self.generate_allergy_list(allergy_score)

	def generate_allergy_list(self, allergy_score):
		allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
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
