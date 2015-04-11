class Allergies:

	def __init__(self, n):
		self.b = bin(n)
		if n > 255:
			self.b = self.b[len(self.b)-8:]
		else:
			self.b = self.b[2:]
			self.b = self.b.zfill(8) 
			
		self.a = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']
		self.list = []
		for i in range(len(self.b)-1,-1,-1):
			if self.b[i] == '1':
				self.list.append(self.a[i])
		print self.list
						
	def is_allergic_to(self, allergen):
		return allergen in self.list
