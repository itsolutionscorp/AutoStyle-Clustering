_allergen_list=['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

# the allergy combination represented by a particular allergy score
class Allergies(object):
	def __init__(self,score):
		self.list=[]
		for i in range(len(_allergen_list)):
			if score & 2**i==2**i:
				self.list.append(_allergen_list[i])

	# determines if an allergy to thing is indicated by this allergy score
	def is_allergic_to(self,thing):
		return thing.lower() in self.list
