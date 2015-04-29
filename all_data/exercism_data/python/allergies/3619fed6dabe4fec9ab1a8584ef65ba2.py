class Allergies():
	allergies = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
	def __init__(self,score):
		self.allergic_to = []
		self.list = []
		for i,x in enumerate(reversed(list(bin(score)[2:].zfill(8)))):
			if int(x):
				self.allergic_to.append(i)
				if i < len(self.allergies):
					self.list.append(self.allergies[i])
	def is_allergic_to(self,allergy):
		return self.allergies.index(allergy) in self.allergic_to
		
