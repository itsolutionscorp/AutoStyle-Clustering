class Allergies(object):

	def __init__(self, score):
		self.list = list(_allrgs(score))
		self.dict = dict.fromkeys(self.list, True)

	def is_allergic_to(self, name):
		return self.dict.get(name, False)


# Generates list of allergy names from test score
def _allrgs(score):
	mask = 1
	for name in 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split(' '):
		if score & mask:
			yield name
		mask = mask << 1
