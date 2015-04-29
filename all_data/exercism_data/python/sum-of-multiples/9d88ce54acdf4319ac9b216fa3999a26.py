#!/usr/bin/python -tt

class SumOfMultiples(object):
	
	def __init__(self,*args):
		self.args = args or (3,5)
	
	def to(self,upBound):
		return sum(i for i in range(1,upBound) if any(i % n == 0 for n in self.args))
