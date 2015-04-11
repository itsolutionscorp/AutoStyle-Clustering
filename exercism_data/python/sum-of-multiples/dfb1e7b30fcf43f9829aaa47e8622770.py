class SumOfMultiples:
	def __init__(self, *args):
		if len(args) != 0:
			self.bases = args;
		else:
			self.bases = [3, 5];
		
	def to(self, n):
		total = 0;
		for i in xrange(1, n):
			add = False
			for b in self.bases:
				if i % b == 0:
					add = True
					break
			if add:
				total += i
		return total

	
