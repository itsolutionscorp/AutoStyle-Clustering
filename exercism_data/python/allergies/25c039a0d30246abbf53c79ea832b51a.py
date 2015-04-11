class Allergies(object):
	def __init__(self, score):
		self.score = score
		self.check_allergies()
		
		
	def check_allergies(self):
		self.list = []
		possible = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()
		results = bin(self.score)[:1:-1].ljust(8,'0')
		for i in range(len(possible)):
			if results[i] == '1':
				self.list.append(possible[i])
				
	def is_allergic_to(self, food):
		if food in self.list:
			return True
		return False
