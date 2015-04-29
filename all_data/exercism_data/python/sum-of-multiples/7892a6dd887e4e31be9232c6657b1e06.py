#!/usr/bin/python -tt

class SumOfMultiples(object):
	
	def __init__(self,*args):
		self.args = args or (3,5)
	
	def is_multiple(self,num):
		for n in self.args:
			if num%n == 0:
				return True
		return False
	
	def to(self,upBound):
		out=0
		for i in range(1,upBound):
			if self.is_multiple(i):
				out+=i
		return out
