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

	def is_allergic_to(self, allergy):
		return self.score & 1 << _allergies.index(allergy)

	@property
	def list(self):
		return [allergy for allergy in _allergies if self.is_allergic_to(allergy)]
