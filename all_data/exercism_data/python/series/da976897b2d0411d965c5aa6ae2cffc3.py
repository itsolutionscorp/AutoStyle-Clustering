from string import letters, punctuation, whitespace

class Series:
	def __init__(self,s):
		self.l = [int(c) for c in s.translate(None, letters+punctuation+whitespace)]
	
	def slices(self,n):
		if n < 1 or n > len(self.l):
			raise ValueError("Invalid slice length for this series: %d" % n)
		return [self.l[i:i+n] for i in range(len(self.l)-n+1)]