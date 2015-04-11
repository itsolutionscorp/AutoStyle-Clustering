class Allergies:

	def is_allergic_to(self, thing):
		return thing in self.list

	def get_list(self, rating):
		# Powers of 2.
		results = []
		ratings = [2 ** x for x in range(0, len(self.things))]
		for i, item in enumerate(self.things):
			# Bitwise AND is not 0.
			if rating & ratings[i]:
				results.append(item)
		return results

	def __init__(self, rating):
		self.things = ['eggs', 'peanuts', 'shellfish', 'strawberries',
						'tomatoes', 'chocolate', 'pollen', 'cats']
		self.rating = rating
		self.list = self.get_list(rating)
