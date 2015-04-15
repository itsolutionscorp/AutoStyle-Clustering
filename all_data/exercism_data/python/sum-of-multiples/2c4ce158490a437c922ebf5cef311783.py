class SumOfMultiples:
	multiples = (3,5)
	
	def to(self,index):
		sum = 0
		for m in self.multiples:
			for current in range(1,index):
				if current % m == 0:
					sum += current
		return sum
		
	def __init__(self,*mult):
		if len(mult):
			self.multiples = mult
		return
