from __future__ import division

class SumOfMultiples():
	def __init__(self, *factors):
		if not factors:
			self.factors = (3, 5)
		else:
			self.factors = factors
	
	def to(self, limit):
		total = set()
		for factor in self.factors:
			flimit, mod = divmod(limit, factor)
			if mod == 0:
				flimit -= 1
			for i in range(1, flimit + 1):
				total.add(factor * i)
		return sum(total)
