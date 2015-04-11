class SumOfMultiples:

	def __init__(self, *numbers):
		if numbers:		
			self.factors = numbers
		else:
			self.factors = [3, 5]

	def to(self, limit):
		sum = 0
		for testnumber in range(1, limit):
			for factor in self.factors:
				if testnumber % factor == 0:
					sum += testnumber
					break
		return sum
