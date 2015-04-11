from string import letters, punctuation, whitespace

class Series(str):
	def __init__(self,s):
		str.__init__(self, s.translate(None, letters+punctuation+whitespace))
	
	def slices(self,n):
		if n == 0 or n > len(self):
			raise ValueError("Invalid slice length for this series: %d" % n)
		return [[int(c) for c in self[i:i+n]] for i in range(len(self)-n+1)]
		
