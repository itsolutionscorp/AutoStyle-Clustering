class SumOfMultiples:
	'''
	Given a number (limit), can find the sum of all the multiples of 3 or 5 up to but not including that number
	If factors passed as argument, use them instead of 3 and 5
	'''
	
	def __init__(self, *factors):
		if len(factors)<1:
			self.factors = (3,5)
		else:
			self.factors = factors
	
	def to(self,limit):
		multlist = []
		for factor in self.factors:
			multlist.extend(list(range(factor,limit,factor)))
		multlist = list(set(multlist))
		return int(sum(multlist))

		
if __name__ == '__main__':
	SumOfMultiples().to(10)
	SumOfMultiples(2).to(10)
#	SumOfMultiples(43, 47).to(10000)
	SumOfMultiples().to(1)
	
