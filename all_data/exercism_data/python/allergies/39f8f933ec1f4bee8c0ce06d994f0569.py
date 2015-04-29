class Allergies:

	allergiesNames = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

	def __init__(self, number):
		self.list = []
		for index in range(0, len(self.allergiesNames)):
			if number & 2 ** index != 0:
				self.list.append(self.allergiesNames[index])

	def is_allergic_to(self, allergen):
		return allergen in self.list
