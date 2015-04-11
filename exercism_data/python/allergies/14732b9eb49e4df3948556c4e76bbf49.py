from itertools import combinations
import itertools

possible_allergies = {'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8, 'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128}


def allergy_list(set):
	true_allergy = []
	false_allergy = []
	y = dict((v,k) for k,v in possible_allergies.items())
	for x in set:
		true_allergy.append(y[x])
		del y[x]
	false_allergy = list(y.values())
	return true_allergy


class Allergies(object):
	
	
	def __init__(self, score):
		self.score = score
		self.list=self.get_list()

	def summation(self, numbers, target):
		results = []
		for x in range(len(numbers)+1):
			results.extend([c for c in combinations(numbers,x) if sum(c) == target])
			merged = list(itertools.chain.from_iterable(results))
		return merged

	def get_list(self):
		if self.score > 256:
			self.score = self.score - 256
		x = [v for k,v in possible_allergies.items() if v <= self.score]
		set = self.summation(x,self.score)
		return allergy_list(set)


	def is_allergic_to(self, type):
		y = self.get_list()
		print y
		if not y:
			return False
		for i in y:
			if i == type:
				return True
		return False
