class Allergies:
	# Class variables
	allergy_dict = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries', \
					16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'}
	list = []

	def __init__(self, allergy_score):
		self.score = allergy_score

		# Generate an allergy list based on the score.
		self.allergy_list(self.score)
		
	def is_allergic_to(self, allergen):
		if allergen in self.list: return True
	
	def allergy_list(self, score):
		# Clear the allergy list.
		self.list = []
		
		# Logically AND the score with each bit value to see if that bit is set.
		# Sort the dictionary keys so that we test in a known order.
		for allergy_num in sorted(self.allergy_dict):
			if score & allergy_num == allergy_num:
				self.list.append(self.allergy_dict[allergy_num])
