class SumOfMultiples(object):

	def __init__(self, *args):
		self.mults = args if args else [3,5]
	
	def to(self, num):
		return sum([i for i in range(1,num) if any([i%j == 0 for j in self.mults])])
