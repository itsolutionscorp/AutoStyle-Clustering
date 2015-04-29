class TriangleError(Exception): pass

def isolate(tuple):
	for i in range(len(tuple)):
		yield tuple[i], tuple[:i] + tuple[i+1:]

class Triangle:
	def __init__(self, *args):
		self.sides = args

		if any(side <= 0 for side in args):
			raise TriangleError("side <= 0")
		if any(side >= sum(rest) for side, rest in isolate(args)):
			raise TriangleError("triangle inequality violated")

	def kind(self):
		cnt = len(set(self.sides))
		if cnt == 1:
			return "equilateral"
		elif cnt == 2:
			return "isosceles"
		else:
			return "scalene"
