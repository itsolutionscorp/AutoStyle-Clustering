class Allergies:

	_allergies = [
		'eggs',
		'peanuts',
		'shellfish',
		'strawberries',
		'tomatoes',
		'chocolate',
		'pollen',
		'cats'
	]

	def __init__(self, score):
		self.score = score
		self.list = []
		for allergy in self._allergies:
			if self.is_allergic_to(allergy):
				self.list.append(allergy)

	def is_allergic_to(self, allergy):
		index = self._allergies.index(allergy)
		return self.score & 1 << index
