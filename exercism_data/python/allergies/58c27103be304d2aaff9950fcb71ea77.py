#/usr/bin/env python
class Allergies:
	def __init__(self,score):
		self.score = score
		self.list = []
		self.scar = self.score
		if self.scar >= 128:
			self.list.append('cats')
			self.scar -= 128
		if self.scar >= 64:
			self.list.append('pollen')
			self.scar -= 64
		if self.scar >= 32:
			self.list.append('chocolate')
			self.scar -= 32
		if self.scar >= 16:
			self.list.append('tomatoes')
			self.scar -= 16
		if self.scar >= 8:
			self.list.append('strawberries')
			self.scar -= 8
		if self.scar >= 4:
			self.list.append('shellfish')
			self.scar -= 4
		if self.scar >= 2:
			self.list.append('peanuts')
			self.scar -= 2
		if self.scar >= 1:
			self.list.append('eggs')
			self.scar -= 1
		if self.score > 255:
			self.list = ['eggs']
		self.list = self.list[::-1]
	def is_allergic_to(self, alle):
		if alle in self.list:
			return True
		else:
			return False
