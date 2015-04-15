'''allergies.py
	created  2 Oct 2014
	by @jestuber '''

class Allergies(object):
	"""docstring for Allergies"""
	def __init__(self, score):
		super(Allergies, self).__init__()
		self.score = score
		items = (('eggs',1),
				('peanuts',2),
				('shellfish',4),
				('strawberries',8),
				('tomatoes',16),
				('chocolate',32),
				('pollen',64),
				('cats',128),
				)
				
		self.list = []
		for allergen, value in items:
			if score & value:
				self.list.append(allergen)


		
	def is_allergic_to(self, allergen):
		return allergen in self.list
