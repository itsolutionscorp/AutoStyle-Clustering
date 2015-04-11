ALLERGY_INDEX = ('eggs',
				'peanuts',
				'shellfish',
				'strawberries',
				'tomatoes',
				'chocolate',
				'pollen',
				'cats')

class Allergies(object):

	def __init__(self, allergy_score):
		self.list = self.list_of_allergies(allergy_score)

	def is_allergic_to(self, allergy):
		return allergy in self.list

	def list_of_allergies(self, allergy_score):
		allergy_list = []

		for i in range(8):
			if allergy_score & 2**i:
				allergy_list.append(ALLERGY_INDEX[i])
		
		return allergy_list
