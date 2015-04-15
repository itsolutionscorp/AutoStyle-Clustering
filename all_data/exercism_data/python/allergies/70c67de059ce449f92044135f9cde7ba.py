class Allergies():
	allergens = {  1 : 'eggs', 
								2 : 'peanuts', 
								4 : 'shellfish',
								8 : 'strawberries',
								16 : 'tomatoes',
								32 : 'chocolate',
								64 : 'pollen',
								128 : 'cats'}
	sorted_scores = sorted(allergens.keys(), reverse = True)
	
	def __init__(self, allergy_score):
		self.allergy_score = allergy_score
		self.list = []
		self.find_allergies(self.allergy_score)
	
	def find_allergies(self, allergy_score):
		for score in self.sorted_scores:
			if score <= allergy_score:
				self.list.insert(0, self.allergens[score])
				allergy_score %= score
		
	def is_allergic_to(self, allergen):		
		if allergen in self.list:
			return True
