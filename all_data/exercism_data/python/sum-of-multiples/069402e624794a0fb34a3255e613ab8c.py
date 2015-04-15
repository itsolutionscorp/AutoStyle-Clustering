

class SumOfMultiples:

	def __init__(self, *args):
		self.multiple = [3, 5]
		self.values = []
		
		if args:
			self.multiple = list(args)
			
	
	def to(self, _range):
		for val in self.multiple:
			self.values.extend([x for x in range(1,_range) if x % val == 0 ])
	
		return sum(set(self.values))
		
