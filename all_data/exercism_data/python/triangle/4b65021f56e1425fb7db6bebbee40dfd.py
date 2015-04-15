class TriangleError(Exception):
	def __init__(self):
		pass

class Triangle(object):
	def __init__(self,a,b,c):
		self.sides = [a,b,c]
		self.check()
	
	
	def check(self):
		for side in self.sides:
			if side <= 0 or sum(self.sides)-side <= side:
				raise TriangleError()
	
	def kind(self):
		res = ["","equilateral","isosceles","scalene"]
		return res[len(set(self.sides))]
			
				
