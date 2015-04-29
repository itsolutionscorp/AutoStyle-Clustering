class Allergies:

	allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

	def __init__(self, score):
		self.list = [self.allergens[index] for index in xrange(len(self.allergens)) if score & 2 ** index != 0]

	def is_allergic_to(self, allergen):
		return allergen in self.list
