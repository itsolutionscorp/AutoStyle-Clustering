allergies = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]


class Allergies(object):
	def __init__(self, score):
		self.list = []

		for i, item in enumerate(allergies):
			if pow(2, i) & score:
				self.list.append(item)
		
	def is_allergic_to(self, item):
		return item in self.list
