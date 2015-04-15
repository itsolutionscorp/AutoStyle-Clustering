class Allergies():
	def __init__(self, score):
		self.list = []
		self.allergens = ['eggs', 'peanuts', 'shellfish',
				  'strawberries', 'tomatoes',
				  'chocolate', 'pollen', 'cats']
		for i in range(0, 8):
			if score & 1:
				self.list.append(self.allergens[i])
			score = score >> 1

	def is_allergic_to(self, allergen):
		return True if allergen in self.list else False
