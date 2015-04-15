class SumOfMultiples(object):

	def __init__(self, *args):
		self.multiples = [3, 5] if not args else args

	def to(self, n):
		sum = 0
		for i in xrange(1, n):
			if any(i % x == 0 for x in self.multiples):
				sum += i
		return sum
