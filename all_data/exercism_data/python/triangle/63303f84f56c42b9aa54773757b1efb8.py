class Triangle:

	def _check(self):
		s = [i for i in self.sides] # Avoid scope issues/copy
		for i in s:
			if i <= 0:
				raise TriangleError("Not a Triangle")
		maxS = max(s)
		s.remove(maxS)
		if sum(s) <= maxS:
			raise TriangleError("Not a Triangle")
		return True

	def __init__(self,t1,t2,t3):
		self.sides = [t1,t2,t3]
		self.check = self._check()

	def kind(self):
		t1,t2,t3 = self.sides
		if t1==t2 and t2==t3:
			return "equilateral"
		elif t1==t2 or t2==t3 or t3==t1:
			return "isosceles"
		else:
			return "scalene"

class TriangleError(Exception):
	def __init__(self,message=""):
		self.message=message
	def __str__(self):
		return repr(self.message)
