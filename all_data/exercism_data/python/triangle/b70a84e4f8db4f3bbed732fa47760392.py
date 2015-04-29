class TriangleError(Exception):
	pass

class Triangle:
	TRIANGLES = ["equilateral", "isosceles", "scalene"]

	def __init__(self, *args):
		s = list(args)
		s.sort()
		if len(s) != 3 or s[0] == 0 or s[2] >= s[0]+s[1]:
			raise TriangleError
		self.sides = s
	
	def kind(self):
		s = set(self.sides)
		return self.TRIANGLES[len(s)-1]
