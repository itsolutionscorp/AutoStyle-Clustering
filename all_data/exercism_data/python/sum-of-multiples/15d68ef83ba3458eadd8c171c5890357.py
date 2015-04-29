class SumOfMultiples:
	
	numbers = (3, 5)

	def __init__(self, *nums):
		if nums != ():
			self.numbers = nums
	
	def to(self, limit):
		total = 0
		for m in self.numbers:
			n = int(limit/m)
			# This conditional excludes the last multiple in case that
			# the limit is actually a multiple of 'm'.
			if limit % m == 0: n -= 1
			if n > 0:
				# This is based on the summation formulas.
				total += m * (n * (n + 1) / 2)
		return total
