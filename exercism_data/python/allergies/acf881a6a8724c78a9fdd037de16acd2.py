class Allergies:

	def __init__(self, n):
		allergens = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()
		self.list = [item for (item, shift) in zip(allergens, range(len(allergens))) if n >> shift & 1]

	def is_allergic_to(self, item):
		return item in self.list
