#/usr/bin/env python
class Allergies:
	possibs = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}
	def __init__(self,score):
		self.scar = score
		self.list = []
		for aa in sorted(self.possibs.keys()):
			if aa & self.scar:
				self.list.append(self.possibs[aa])
	def is_allergic_to(self, alle):
		if alle in self.list:
			return True
		else:
			return False
