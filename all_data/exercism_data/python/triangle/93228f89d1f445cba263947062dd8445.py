class Triangle():
	def __init__(self, a, b, c):
		if a <= 0 or b <= 0 or c <= 0:
			raise TriangleError('Triangle must have sides longer than 0.')

		if not abs(a - b) < c < a + b:
			raise TriangleError('Triangle violates inequality.')

		self.a = a
		self.b = b
		self.c = c

	def kind(self):
		if self.a == self.b and self.b == self.c:
			return 'equilateral'
		elif self.a == self.b or self.b == self.c or self.a == self.c:
			return 'isosceles'
		else:
			return 'scalene'

class TriangleError(Exception):
	def __init__(self, value):
		self.value = value
	def __str__(self):
		return repr(self.value)
