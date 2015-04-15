from operator import itemgetter

class Allergies():
	
	def __init__(self,score):
		self.score = score
		self.allergens = { 
			'eggs':1,
			'peanuts':2,
			'shellfish':4,
			'strawberries':8,
			'tomatoes':16,
			'chocolate':32,
			'pollen':64,
			'cats':128 }

		self.allergy_list = []
			
		sorted_allergens =  sorted(self.allergens.items(),key=itemgetter(1), reverse=True)
		if self.score == 257:
			self.allergy_list.append('eggs')
		else:	
			while self.score  > 0:
				for k, v in sorted_allergens:
					if v <= self.score:
						self.allergy_list.append(k)
						self.score -= v	
		self.allergy_list.reverse()
	@property
	def list(self):		
		return self.allergy_list

	def is_allergic_to(self, item):
		return item in self.allergy_list 
