class Allergies(object):

	def __init__(self, allergy_score):
		self.allergy_score = allergy_score
		self.list = self.list_of_allergies()

	def is_allergic_to(self, food):
#		allergy_status = False
#		for thing in self.list:
#			if thing == food:
#				allergy_status = True
#		return allergy_status
		return any(food is item for item in self.list)

	def list_of_allergies(self):
		allergy_score = self.allergy_score
		allergy_list = []
		allergy_index =	{'eggs':1, 
						'peanuts':2, 
						'shellfish':4, 
						'strawberries':8, 
						'tomatoes':16, 
						'chocolate':32, 
						'pollen':64, 
						'cats':128}

		if allergy_score > 255:
			allergy_score-=256

		if allergy_score - allergy_index['cats'] >= 0:
			allergy_list.append('cats')
			allergy_score-=allergy_index['cats']

		if allergy_score - allergy_index['pollen'] >= 0:
			allergy_list.append('pollen')
			allergy_score-=allergy_index['pollen']

		if allergy_score - allergy_index['chocolate'] >= 0:
			allergy_list.append('chocolate')
			allergy_score-=allergy_index['chocolate']

		if allergy_score - allergy_index['tomatoes'] >= 0:
			allergy_list.append('tomatoes')
			allergy_score-=allergy_index['tomatoes']

		if allergy_score - allergy_index['strawberries'] >= 0:
			allergy_list.append('strawberries')
			allergy_score-=allergy_index['strawberries']

		if allergy_score - allergy_index['shellfish'] >= 0:
			allergy_list.append('shellfish')
			allergy_score-=allergy_index['shellfish']

		if allergy_score - allergy_index['peanuts'] >= 0:
			allergy_list.append('peanuts')
			allergy_score-=allergy_index['peanuts']

		if allergy_score - allergy_index['eggs'] >= 0:
			allergy_list.append('eggs')
			allergy_score-=allergy_index['eggs']

		return list(reversed(allergy_list))
