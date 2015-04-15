# sum_of_multiples

			
class SumOfMultiples:
	def __init__(self, *args):
		if len(args) == 0:
			self.mults=(3,5)
		else:
			self.mults = args
		
	def _testForMultiple(self, n):
		for i in self.mults:
			if n % i == 0:
				return True
		return False
	
	def to(self, n):
		sumOfMult = 0
		for i in range(1,n):
			if self._testForMultiple(i):
				sumOfMult = sumOfMult + i
		return sumOfMult
