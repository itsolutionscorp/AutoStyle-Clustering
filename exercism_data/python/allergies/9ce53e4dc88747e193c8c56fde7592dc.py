class Allergies:
	
	def __init__(self, alscore):
		self.alscore = alscore
		self.list = self.allergy_list()
		print alscore

	def allergy_list(self):
		alref = ['eggs', 'peanuts', 'shellfish', 'strawberries',
				 'tomatoes', 'chocolate', 'pollen', 'cats']
		resultlist = []

		albin = bin(self.alscore).lstrip('0b')
		albin = list(albin)[::-1]

		for i in xrange(len(albin)):
			if albin[i] == '1' and i < 8:
				resultlist.append(alref[i])

		return resultlist

	def is_allergic_to(self, food):
		return food in self.allergy_list()





		
		
