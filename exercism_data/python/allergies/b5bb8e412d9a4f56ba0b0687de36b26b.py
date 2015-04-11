class Allergies:
	
	allergens = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries', 16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'}

	def build_list(self, allergyCode):
		if ( allergyCode > sum( self.allergens.keys() ) ):
			allC = allergyCode % ( sum( self.allergens.keys() ) + 1 )
		else:	allC = allergyCode
		element = 128
		while ( allC > 1 ):
			if ( allC / element >= 1 ):
				self.list = [self.allergens[element]] + self.list
				allC = allC - element
			element = element / 2
		if ( allC == 1 ):
			self.list = [self.allergens[1]] + self.list
	
	def __init__(self, allergyCode):
		self.list = []
		self.build_list(allergyCode)

	def is_allergic_to(self, maybeAllergen):
		if ( maybeAllergen in self.list ): return True
		else: return False
