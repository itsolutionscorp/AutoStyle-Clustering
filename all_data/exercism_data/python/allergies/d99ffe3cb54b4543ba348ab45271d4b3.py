class Allergies(object):

	def __init__(self,score):
		self.allergies = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
		self.allergyIndex = {'eggs':0,'peanuts':1,'shellfish':2,'strawberries':3,'tomatoes':4,'chocolate':5,'pollen':6,'cats':7}
		self.score = score % 256
		self.list = []
		for allergen in self.allergies:
			if self.score & 2**self.allergyIndex[allergen]:
				self.list.append(allergen)

	def is_allergic_to(self,allergen):
		'''
		used bitwise AND to determine if specific bit is present in score
		'''
		return bool(self.score & 2**self.allergyIndex[allergen])
