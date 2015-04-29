## Thanks to mherk

class Allergies(object):
	def __init__(self, score):
		self.score = score
		self.allergyList = [ 'eggs',
							'peanuts',
							'shellfish',
							'strawberries',
							'tomatoes',
							'chocolate',
							'pollen',
							'cats'
		]
		self.list = []
		if len(bin(self.score)[2:]) > 8:
			binscore = list(bin(self.score)[3:])
		else:
			binscore = list(bin(self.score)[2:])
		for i in range(1, len(binscore) + 1):
			if binscore[-i] == '1':
				self.list.append(self.allergyList[i-1])

	def is_allergic_to(self, allergen):
		if allergen in self.list:
			return True
		else:
			return False
		
