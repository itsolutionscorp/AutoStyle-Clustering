class Allergies:

	def __init__(self, score):
		self.list = [
			'eggs',
			'peanuts',
			'shellfish',
			'strawberries',
			'tomatoes',
			'chocolate',
			'pollen',
			'cats'
		]

		self.list = [x for x in self.list if 1 << self.list.index(x) & score]

	def is_allergic_to(self, allergen):
		return allergen in self.list
