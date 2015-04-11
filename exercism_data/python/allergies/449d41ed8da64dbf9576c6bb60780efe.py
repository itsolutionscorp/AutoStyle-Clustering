from collections import OrderedDict
class Allergies():
	score = 0
	allergens = OrderedDict([('eggs',1),('peanuts',2),('shellfish',4),('strawberries',8),('tomatoes',16),('chocolate',32),('pollen',64),('cats',128)])
	list = []
	def __init__(self, score):
		self.score = score
		self.compile_list()
	def is_allergic_to(self, allergen):
		return (self.score & self.allergens[allergen])
	def compile_list(self):
		self.list = []
		for allergen in self.allergens:
			if self.is_allergic_to(allergen) and allergen not in self.list:
				self.list.append(allergen)
