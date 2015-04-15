class Allergies:

	ALLERGIES_LIST = [
		'eggs', 
		'peanuts', 
		'shellfish', 
		'strawberries', 
		'tomatoes', 
		'chocolate',
		'pollen',
		'cats',
	]

	def __init__(self, n):
		self.list = []
		numAllergies = len(Allergies.ALLERGIES_LIST)
		n = n % 2**numAllergies
		for i in range(numAllergies - 1, -1, -1):
			if n >= 2**i:
				self.list.append(Allergies.ALLERGIES_LIST[i])
				n -= 2**i
		self.list.reverse()

	def is_allergic_to(self, allergy):
		return allergy in self.list
