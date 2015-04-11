class Allergies(object):
	"""Given a person's allergy score, can tell them whether or not they're allergic to a given item, and their full list of allergies."""
	
	allergiesD0 = {'eggs':1, 'peanuts':2, 'shellfish':4, 'strawberries':8, 'tomatoes':16, 'chocolate': 32, 'pollen':64, 'cats':128}
	allergiesD1 = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries', 16:'tomatoes', 32 :'chocolate', 64 :'pollen', 128 :'cats'}
	eggs = 1
	peanuts = 2
	shellfish = 4
	strawberries = 8
	tomatoes = 16
	chocolate = 32
	pollen = 64
	cats = 128	

	def __init__(self, score):
		while score >= 256:
			score -= 256
		self.score = score
		self.alist = [self.cats, self.pollen, self.chocolate, self.tomatoes, self.strawberries , self.shellfish, self.peanuts,self.eggs]
		self.list = self.allergicto()

	def allergicto(self):
		testfor = []
		testscore = self.score
		for i in self.alist:
			if i <= testscore:
				testfor.insert(0, self.allergiesD1[i])
				testscore -= i
		
		return testfor

	def is_allergic_to(self, thing):
		
		if thing in self.list:
			return True
		return False
