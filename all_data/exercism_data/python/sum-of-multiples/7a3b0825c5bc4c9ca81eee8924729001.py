class SumOfMultiples:
	def __init__(self, *args):
		self.args = args if args else [3,5]

	def to(self, number):
		return sum(x for x in range(min(self.args),number)
		if any(x%y == 0 for y in self.args))

eg = SumOfMultiples()
print(eg.to(10))
print(eg.to(1))
print(eg.to(1000))
print(SumOfMultiples(7,13,17).to(20))


		
