# allergies.py


allergylist={ 'eggs': 0,
			'peanuts': 1,
			'shellfish': 2,
			'strawberries': 3,
			'tomatoes': 4,
			'chocolate': 5,
			'pollen': 6,
			'cats': 7 }
			
class Allergies:
	def __init__(self,score):
		self.score = score
		self.list = []
		for allergy in sorted(allergylist, key=allergylist.get):
			if self.is_allergic_to(allergy):
				self.list.append(allergy)	
	def is_allergic_to(self, item):
		return self.score & 2 ** allergylist[item] != 0
		
