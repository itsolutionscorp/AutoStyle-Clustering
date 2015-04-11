class SumOfMultiples():

	def __init__(self, *multiples):
		if not multiples:
			multiples = [3,5]
		self.multiples = multiples

	def to(self, number):
		return sum([o for o in range(number) if any(o%i == 0 for i in self.multiples)])
