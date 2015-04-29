from collections import OrderedDict

class Allergies(object):
	def __init__(self, score):
		self.score = score
		self.list = self.get_allergen_list()

	def is_allergic_to(self, allergen):
		return allergens[allergen] & self.score

	def get_allergen_list(self):
	 	return [x for x in allergens if self.is_allergic_to(x)]

allergens = OrderedDict(
	sorted({
		'eggs': 1,
		'peanuts': 2,
		'shellfish': 4,
		'strawberries': 8,
		'tomatoes': 16,
		'chocolate': 32,
		'pollen': 64,
		'cats': 128
		}.items(), key=lambda d: d[1])
 	)
