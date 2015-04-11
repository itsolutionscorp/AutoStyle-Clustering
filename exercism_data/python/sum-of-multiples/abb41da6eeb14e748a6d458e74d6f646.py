class SumOfMultiples(object):

	def __init__(self, *factors):
		self.factorList = list(factors) or [3, 5]

	def to(self, num):
		sum = 0
		for i in range(1,num):
			for j in self.factorList:
				if i%j == 0:
					sum += i
					break
		return sum
