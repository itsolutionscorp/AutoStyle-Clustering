class Allergies(object):
	"""docstring"""
	
	def __init__(self, testScore):
		self.testScore = testScore % 256
		self.allergens = {1: 'eggs',
		     	     	  2: 'peanuts',
		     	     	  4: 'shellfish',
		     	     	  8: 'strawberries',
		     	     	  16: 'tomatoes',
		     	     	  32: 'chocolate',
		     	     	  64: 'pollen', 
		     	     	  128: 'cats'
		     	     	 }
		self.allergicTo = [] 
		for i in range(7,-1,-1):
			if self.testScore >= (2**i):
				self.testScore -= 2**i
				self.allergicTo.append(self.allergens[2**i])
		self.allergicTo.reverse()
	@property
	def list(self):	
		return self.allergicTo
	
	def is_allergic_to(self, givenAllergen):
		return givenAllergen in set(self.allergicTo)

