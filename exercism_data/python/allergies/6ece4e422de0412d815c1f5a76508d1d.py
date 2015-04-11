from itertools import combinations

class Allergies(object):

	def __init__(self,allergy_indicator):
		self.allergy_indicator = allergy_indicator
		self.list = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}
		self.allergy_map = []

		val_list = list(self.list.keys())
		if self.allergy_indicator >= 256:
			self.allergy_indicator = allergy_indicator - 256
		self.find_sum_in_list(val_list,self.allergy_indicator)
		self.match_allergies()
	def is_allergic_to(self, substance):
		if substance in self.list:
			return True
		else:
			return False

	def find_sum_in_list(self, numbers, allergy_indicator):
		results = []
		if sum(numbers) == allergy_indicator:
			self.allergy_map = list(self.list.keys())
			return	
		for x in range(len(numbers)):
			results.extend([combo for combo in combinations(numbers ,x) if sum(combo) == allergy_indicator])
		self.allergy_map = list(results[0])

	def match_allergies(self):
		
		list_numbers = [x for x in self.list if x not in self.allergy_map]

		for x in list_numbers:
			del self.list[x]
		self.list = list(self.list.values())


