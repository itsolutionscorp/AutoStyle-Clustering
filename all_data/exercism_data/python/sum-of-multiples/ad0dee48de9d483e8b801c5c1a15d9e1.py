class SumOfMultiples:
	def __init__(self, *args):
		self.multiples = [value for count, value in enumerate(args)]
		if not self.multiples:
			self.multiples = [3, 5]


	def to(self, limit):
		return sum(value for value in range(limit) if any(value % multiple == 0 for multiple in self.multiples))
