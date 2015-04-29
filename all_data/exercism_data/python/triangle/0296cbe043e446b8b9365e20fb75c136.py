# represents a triangle
class Triangle(object):
	_kinds=["equilateral","isosceles","scalene"]

	def __init__(self,a,b,c):
		if a<=0 or b<=0 or c<=0:
			raise TriangleError("Triangles cannot have zero or negative side length.")
		if a+b<=c or a+c<=b or b+c<=a:
			raise TriangleError("Triangles must satisfy the triangle inequality.")
		self.sides=sorted([a,b,c])

	def kind(self):
		return Triangle._kinds[len(set(self.sides))-1]

# some sort of error was encountered when constructing a Triangle
class TriangleError(Exception):
	def __init__(self,message):
		super(TriangleError,self).__init__(message)
