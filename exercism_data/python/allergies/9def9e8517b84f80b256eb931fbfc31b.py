class Allergies:
	ALLERGIES = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

	def __init__(self, value):
		self.list = []
		for I in range(0, len(self.ALLERGIES)):
			if (self._test_bit(value, I)):
				self.list.append(self.ALLERGIES[I])

	def _test_bit(self, int_type, offset):
	    return (int_type & (1 << offset))

	def is_allergic_to(self, allergen):
		return allergen in self.list
