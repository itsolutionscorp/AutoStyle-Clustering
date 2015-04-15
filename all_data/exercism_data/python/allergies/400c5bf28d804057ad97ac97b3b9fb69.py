class Allergies:
	
	allergen_list = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
	allergen_codes = [1,2,4,8,16,32,64,128]

	def __init__(self, allergen_score):
		self.list = Allergies.make_list(self, allergen_score)
		self.allergen_score = allergen_score
	
	def make_list(self, score):
		temp_list = []
		for name,code in zip(Allergies.allergen_list, Allergies.allergen_codes):
			#Bitwise comparison
			#"non_allergen_score_parts are bits in the binary representation of the allergen score that are greater than 255"
			if code & score:
				temp_list.append(name)
				score-=code
		return temp_list

	def is_allergic_to(self, allergen):
		return allergen in self.list
