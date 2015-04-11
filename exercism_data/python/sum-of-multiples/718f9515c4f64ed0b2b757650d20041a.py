class SumOfMultiples():
	def __init__(self,*args):
		if len(args)== 0:
			self.vals = [3,5]
		else:
			self.vals = sorted(map(int,args))
	def to(self,n):
		vals = self.vals
		ret = []
		for i in vals:
			k = 1
			while k*i < n:
				ret.append(k*i)
				k += 1
		return sum(list(set(ret)))
