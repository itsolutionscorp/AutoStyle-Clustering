def divisible(target, factors):
	return reduce(lambda m, n: m or not target % n , factors, False) # reduce with initializer False

class SumOfMultiples:
	def __init__(self, *factors): # takes arbitrary tuples of arguments
		self.factors = factors or (3,5)

	def to(self, limit):
		return sum([m for m in range(1, limit) if divisible(m, self.factors)])  # sum of all unique multiples
