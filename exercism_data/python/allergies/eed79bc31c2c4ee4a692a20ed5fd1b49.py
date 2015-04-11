class Allergies:
	allergies = [
		'eggs', 'peanuts', 'shellfish',
		'strawberries', 'tomatoes', 'chocolate',
		'pollen', 'cats'
	]

	def __init__(self, value):
		self.value = value
	
	def is_allergic_to(self, alergy):
		try:
			idx = Allergies.allergies.index(alergy)
			return self.value & 1 << idx			
		except ValueError:
			return False
			
	def __getattr__(self, key):
		if key == 'list':
			return [Allergies.allergies[i] for i in xrange(len(Allergies.allergies)) if self.value & 1 << i]
		else:
			raise AttributeError()
