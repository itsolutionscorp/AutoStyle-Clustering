# exercism sum_of_multiples

# sum the multiples of a given numbers, up to a limit exclusive
class SumOfMultiples:
	multiples = []

	def __init__(self, *multiples):
		if len(multiples):
			self.multiples = multiples
		else:
			self.multiples = [3,5]

	def to(self, limit):
		sum = 0
		for x in range(limit):
			for divisor in self.multiples:
				if x % divisor == 0:
					sum += x
					break

		return sum
