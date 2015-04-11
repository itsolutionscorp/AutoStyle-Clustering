class Triangle(object):
	def __init__(self, a, b, c):
		self.sides = [a, b, c]

	def kind(self):
		side = self.sides[2]

		if side < 0:
			print "Hello"

	def check_basic(self, side):
		if side < 0:
			raise TriangleError


class TriangleError(Exception):
	def __init__(self, value):
		self.value = value
