def divisible(target, factors):
	return not all(map(lambda x: target % x, factors))

print divisible(10000, (43, 47))

class SumOfMultiples:
	def __init__(self, *factors): # takes arbitrary tuples of arguments
		self.factors = factors or (3,5)

	def to(self, limit):
		return sum(filter(lambda n: divisible(n, self.factors), range(1, limit)))  # sum of all unique multiples
