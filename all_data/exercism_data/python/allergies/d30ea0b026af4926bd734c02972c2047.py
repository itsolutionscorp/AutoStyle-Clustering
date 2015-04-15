'''allergies.py
	created  2 Oct 2014
	by @jestuber '''

ITEMS = (('eggs',1),
			('peanuts',2),
			('shellfish',4),
			('strawberries',8),
			('tomatoes',16),
			('chocolate',32),
			('pollen',64),
			('cats',128),
			)

class Allergies(object):
	"""a program that, given a person's allergy score, can tell them whether or not they're allergic to a given item, and their full list of allergies."""

	def __init__(self, score):
		super(Allergies, self).__init__()

		self.list = []
		for allergen, value in ITEMS:
			if score & value: #    Does a "bitwise and". Each bit of the output is 1 if the corresponding bit of x AND of y is 1, otherwise it's 0. 
				self.list.append(allergen)

	def is_allergic_to(self, allergen):
		return allergen in self.list
