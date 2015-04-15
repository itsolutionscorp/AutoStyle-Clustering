class Allergies :
	allergen_values = {1:'eggs', 2:'peanuts', 4:'shellfish',8:'strawberries', 16:'tomatoes', 32:'chocolate', 64:'pollen',128:'cats'}
	values = [1,2,4,8,16,32,64,128]

	def __init__(self, score=0):
		self.score = score
		self.allergens = list(reversed(self.get_allergens()))
		self.list = self.set_allergen_names()
		

	def is_allergic_to(self, allergen):
		if allergen in self.list:
			return True
		else:
			return False

		
	def set_allergen_names(self):
		my_list = []
		for a in self.allergens:
			if a in self.allergen_values:

				my_list.append(self.allergen_values[a])
		
		return my_list

	def get_allergens(self):
		allergens_list = []
		score = self.score
		#reversed in order to check biggest value first
		for v in reversed(self.values):
			if score >= v:
				allergens_list.append(v)
				score -= v
		if score > 0:
			allergens_list = [1]
		return allergens_list


	
