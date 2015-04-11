class Allergies:
	ALLERGENS = """eggs peanuts shellfish strawberries
		tomatoes chocolate pollen cats""".split()

	def __init__(self, n):
		self.list = [item for (item, shift)
			in zip(self.ALLERGENS, range(len(self.ALLERGENS)))
			if n >> shift & 1]

	def is_allergic_to(self, item):
		return item in self.list
