allergens = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()

class Allergies:
    def __init__(self, score):
		self.list = []
		for i in range(len(allergens)):
			if (score >> i) & 1 != 0:
				self.list.append(allergens[i])

    def is_allergic_to(self, allergen):
        return allergen in self.list
