class SumOfMultiples:
	def __init__(self, *args):
		self.multiples = args if args else [3,5]

	def to(self, n):
		result = set()
		for m in self.multiples:
			result.update(range(m,n,m))
		return sum(result)
