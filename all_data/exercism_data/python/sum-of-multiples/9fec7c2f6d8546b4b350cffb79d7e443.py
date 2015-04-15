class SumOfMultiples:
	def to(self,index):
		l = set()
		for m in self.multiples:
			for current in range(m,index,m):
				l.update([current])
		return sum(l)
		
	def __init__(self,*mult):
		self.multiples = mult or [3,5]
		return
