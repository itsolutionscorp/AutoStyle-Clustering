allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes','chocolate', 'pollen', 'cats']

class Allergies():

	def __init__(self, allergy_score):
		self.list = [allergies[i] for i in range(len(allergies)) if (allergy_score >> i) & 1 != 0]

	def is_allergic_to(self, allergen):
		return allergen in self.list

