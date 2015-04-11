class Series:
	def __init__(self,s):
		self.l = [int(c) for c in s]
	
	def slices(self, n):
		if n < 1 or n > len(self.l):
			raise ValueError("Invalid slice length for this series: %d" % n)
		return [self.l[i:i+n] for i in range(len(self.l)-n+1)]
	
	def largest_product(self,n):
		return max([reduce(lambda x,y:x*y, l) for l in self.slices(n)]) if n>0 else 1
