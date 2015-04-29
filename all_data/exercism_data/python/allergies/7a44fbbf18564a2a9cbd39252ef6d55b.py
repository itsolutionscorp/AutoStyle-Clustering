class Allergies():
	def __init__(self, score):
		self.score = score
		self.allergens = ['eggs', 'peanuts', 'shellfish',
				  'strawberries', 'tomatoes',
				  'chocolate', 'pollen', 'cats']
		self.list = [x for 
		             id, x in enumerate(self.allergens) 
			     if self.score & 1 << id]

	def is_allergic_to(self, allergen):
		return True if allergen in self.list else False
