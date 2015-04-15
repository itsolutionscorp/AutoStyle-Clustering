class Allergies:
	'''A simple class'''
	
	def __init__(self, score):
		allergens = {
			0:"eggs",
			1:"peanuts",
			2:"shellfish",
			3:"strawberries",
			4:"tomatoes",
			5:"chocolate",
			6:"pollen",
			7:"cats"
			}
		allergen_list = [allergens[ii] for ii in range(0,8) if score & (1<<ii)]
		self.list = allergen_list
		return
		
	def is_allergic_to(self,allergen):
		return allergen in set(self.list)
