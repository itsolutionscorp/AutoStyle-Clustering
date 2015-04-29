class Allergies:
	def __init__(self, allergyScore):
		master_list = ('eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats')
		self.list = []
		allergyDup = allergyScore
		for allergen in master_list:
			if (allergyDup & 1):
				self.list.append(allergen)
			allergyDup = allergyDup >> 1
		
	# Returns True or False for given allergy	
	def is_allergic_to(self, allergy):
		if allergy in self.list:
			return True
		else:
			return False
