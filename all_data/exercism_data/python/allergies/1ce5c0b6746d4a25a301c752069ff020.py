ALLERGY_LIST = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats'
]

class Allergies(object):

	def __init__(self,score):
		#self.score=score
		self.list = self.calc_list(score)

	def is_allergic_to(self,item):
		#print self.list
		return item in self.list
		
	#@classmethod
	def calc_list(self,score):
		return [ALLERGY_LIST[i]  for i in range(8) if score & 1 << i]

		
