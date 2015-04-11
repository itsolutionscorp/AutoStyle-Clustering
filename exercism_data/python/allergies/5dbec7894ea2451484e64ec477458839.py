class Allergies:

	def __init__(self, score):
		self._items = {'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8,
					  'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128}
		self.list = [k for k, v in self._items.items() if v & score == v]

	def is_allergic_to(self, item):
		return item in self.list
