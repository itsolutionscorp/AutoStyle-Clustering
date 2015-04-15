def to1(self, number):
	z = 0 
	for i in range(1,number):
		dividable = False
		for  k in self.dividers:
			if i % k == 0:
				z += i
				break 
	return z

	
class SumOfMultiples:
	to = to1
	def __init__(self,*args):
		
		if len(args)==0:
			self.dividers=[3,5]
		else:
			self.dividers=args
		
