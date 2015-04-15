scoreboard = {1 : 'eggs', 2 : 'peanuts', 4 : 'shellfish',
					8 : 'strawberries', 16 : 'tomatoes',
					32 : 'chocolate', 64 : 'pollen',
					128: 'cats'}


class Allergies:
	def __init__(self, score):
		self.score = score
		code = bin(self.score)
		self.list = []
		keys = scoreboard.keys()
		keys.sort()
		for key in keys:
			if key & self.score:
				self.list.append(scoreboard[key])
		
	
	def is_allergic_to(self, allergen):
		return allergen in self.list
