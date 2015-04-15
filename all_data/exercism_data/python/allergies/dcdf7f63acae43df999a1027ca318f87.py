class Allergies:
	'''
	Given a person's allergy score, tell whether or not they're allergic to a given item, 
	and their full list of allergies
	'''
	
	def __init__(self, score):
		
		allergens = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()
		allergen_list = [allergens[ii] for ii in range(0,8) if score & (1<<ii)]
		self.list = allergen_list
		return
		
	def is_allergic_to(self,allergen):
		return allergen in set(self.list)

if __name__ == '__main__':
	allergies = Allergies(13)
	print(allergies.list)
	print(allergies.is_allergic_to('eggs'))
	print(allergies.is_allergic_to('soy milk'))
