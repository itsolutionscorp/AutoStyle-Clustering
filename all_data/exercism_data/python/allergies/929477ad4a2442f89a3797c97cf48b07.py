class Allergies(object):

	def __init__(self,score):
		self.list = []
		if (score < 256):	
			self.score = score
		else:
			self.score = score - 256

		if (self.score > 127):
			self.list.append('cats')
			self.score = self.score - 128
		
		if (self.score > 63):
			self.list.append('pollen')
			self.score = self.score - 64

		if (self.score > 31):
			self.list.append('chocolate')
			self.score = self.score - 32

		if (self.score > 15):
			self.list.append('tomatoes')
			self.score = self.score - 16
	
		if (self.score > 7):
			self.list.append('strawberries')
			self.score = self.score - 8
		
		if (self.score > 3):
			self.list.append('shellfish')
			self.score = self.score - 4
		
		if (self.score > 1):
			self.list.append('peanuts')
			self.score = self.score - 2

		if (self.score == 1):
			self.list.append('eggs')

		self.list.reverse()

	def is_allergic_to(self, allergy):
		return allergy.lower() in self.list

		
		
		 
