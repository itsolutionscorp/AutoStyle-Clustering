class SumOfMultiples(object):

	def __init__(self, *factors):
		if len(factors) == 0:
			self.factorList = [3, 5]
		else:
			self.factorList = list(factors)

	def to(self, num):
		sum = 0
		for i in range(1,num):
			for j in self.factorList:
				if i%j == 0:
					sum += i
					break
		return sum
