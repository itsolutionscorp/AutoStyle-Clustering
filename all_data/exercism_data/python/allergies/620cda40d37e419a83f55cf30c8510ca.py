class Allergies:
	'''
	Given a person's allergy score, tell whether or not they're allergic to a given item, 
	and their full list of allergies
	'''
	
	def __init__(self, score):
		
		allergens = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()
		self.list = [allergen for ii,allergen in enumerate(allergens) if score & (1<<ii)]
		
	def is_allergic_to(self,allergen):
		return allergen in self.list

if __name__ == '__main__':
	allergies = Allergies(13)
	print(allergies.list)
	print(allergies.is_allergic_to('eggs'))
	print(allergies.is_allergic_to('soy milk'))
