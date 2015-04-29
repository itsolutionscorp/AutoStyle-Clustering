from collections import OrderedDict

class Allergies:

	allergens = OrderedDict([('eggs', 1), ('peanuts', 2), ('shellfish', 4), ('strawberries', 8), ('tomatoes', 16), ('chocolate', 32), ('pollen', 64), ('cats', 128)])

	score = 0

	def __init__(self, num):
		self.score = num
	
	@property
	def list(self):
		return [allergen
			for allergen in self.allergens
			if self.is_allergic_to(allergen)]

	def is_allergic_to(self, item):
		return bool(self.score & self.allergens[item])
