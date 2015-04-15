def divisible(target, factors):
	return reduce(lambda m, n: m or not target % n , factors, False) # reduce with initializer False

class SumOfMultiples:
	def __init__(self, *args): # takes arbitrary tuples of arguments
		if not args: args = (3,5)
		self.factors = args

	def to(self, limit):
		return sum([m for m in range(1, limit) if divisible(m, self.factors)])  # sum of all unique multiples


# print SumOfMultiples().to(10)
