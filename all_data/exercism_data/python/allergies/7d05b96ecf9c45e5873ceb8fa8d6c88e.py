class Allergies:
	def __init__(self, ascore):
		self.allergies = {1: 'eggs',
						2: 'peanuts',
						4: 'shellfish',
						8: 'strawberries',
						16: 'tomatoes',
						32: 'chocolate',
						64: 'pollen',
						128: 'cats'
		}
		
		self.ascore = ascore
		
		self.list = list()
		
		keys = self.allergies.keys()
		keys.sort()
		
		for items in keys:
			if items & ascore:
				self.list.append(self.allergies[items])
		
		
		# while count > 0:
			# nextlowest = max(k for k in self.allergies if k <= count)
			# self.list.insert(0,self.allergies[nextlowest])
			# count = count - nextlowest
			
		
	def is_allergic_to(self, allergen):
		return allergen in self.list
