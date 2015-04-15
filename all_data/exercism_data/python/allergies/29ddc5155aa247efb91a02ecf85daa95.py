class Allergies:

	def __init__(self, n):
		allergens = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()
		self.list = []
		while allergens :
			item = allergens.pop(0)
			if n & 1:
				self.list.append(item)
			n >>= 1

	def is_allergic_to(self, item):
		return item in self.list
