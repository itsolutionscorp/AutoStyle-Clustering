allergies = [ 'eggs',  'peanuts', 'shellfish',
	'strawberries', 'tomatoes', 'chocolate',
	'pollen', 'cats' ]

class Allergies:
	def __init__(self, amount):
		self.list = [allergies[i] for i in range(8) if amount & 1 << i]

	def is_allergic_to(self, item):
		return item in self.list
