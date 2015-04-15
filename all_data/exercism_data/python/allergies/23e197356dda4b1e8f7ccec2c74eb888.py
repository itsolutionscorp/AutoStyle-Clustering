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
		n = n % 256
		for i in range(7, -1, -1):
			if n >= 2**i:
				self.list.append(Allergies.ALLERGIES_LIST[i])
				n -= 2**i
		self.list.reverse()

	def is_allergic_to(self, allergy):
		return allergy in self.list
