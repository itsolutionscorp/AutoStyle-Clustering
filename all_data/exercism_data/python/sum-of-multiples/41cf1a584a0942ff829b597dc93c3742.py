class SumOfMultiples:
	
	numbers = (3, 5)

	def __init__(self, *nums):
		if nums != ():
			self.numbers = nums
	
	def to(self, limit):
		multiples = set()
		for m in self.numbers:
			multiples |= set(range(m, limit, m))
		return sum(multiples)
