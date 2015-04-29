scoreboard = {1 : 'eggs', 2 : 'peanuts', 4 : 'shellfish',
					8 : 'strawberries', 16 : 'tomatoes',
					32 : 'chocolate', 64 : 'pollen',
					128: 'cats'}


class Allergies:
	def __init__(self, score):
		self.score = score % 256
		code = str(bin(self.score))[2:]
		self.allergy_codes = []
		for i in range(len(code)):
			if code[i] == '1':
				self.allergy_codes.append(2**(len(code)-i-1))
		self.list = []
		for num in self.allergy_codes:
			self.list.insert(0,scoreboard[num])
	
	def is_allergic_to(self, allergen):
		return allergen in self.list
