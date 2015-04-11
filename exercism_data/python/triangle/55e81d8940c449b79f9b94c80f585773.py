class Triangle(object):
	def __init__(self, a, b, c):
		self.sides = a, b, c = sorted([a, b, c])
		if not all(side > 0 for side in self.sides):
			raise TriangleError('side lengths must be positive')
		if a + b <= c:
			raise TriangleError('triangle inequality violated')

	def kind(self):
		return {
			1: 'equilateral',
			2: 'isosceles',
			3: 'scalene',
		}[len(set(self.sides))]

class TriangleError(Exception):
	pass
