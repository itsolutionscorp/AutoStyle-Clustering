class SumOfMultiples:
	
	multiples = [3,5]

	def __init__(self, *arg):
		if len(arg):
			self.multiples = arg

	def to(self, limit):
		Sum = 0
		for x in range(1,limit):
			for m in self.multiples:
				if x % m == 0:
					Sum += x
					break
		return Sum
