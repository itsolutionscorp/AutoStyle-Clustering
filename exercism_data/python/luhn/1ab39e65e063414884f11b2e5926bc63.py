class Luhn:
	
	def __init__(self, number = 0):
		self.n = number
		
	def addends(self):
		r = list(str(self.n))[::-1]
		for ind,x in enumerate(r):
			if ind % 2 == 1:
				r[ind] = self.computeSum(r[ind]*2)
			else:
				r[ind] = int(r[ind])
		return r
		
	def is_valid(self):
		return (sum([int(x) for x in self.addends()])) % 10 == 0
	
	def checksum(self):
		return sum(self.addends()) % 10
	
	def computeSum(self,num):
		if num < 10:
			return num
		else:
			return self.computeSum(sum((int(x) for x in list(str(num)))))
	
	@staticmethod
	def create(num):
		initial = Luhn(int(str(num)+'0')).checksum()
		if initial % 10 == 0:
			return int(str(num)+'0')
		else:
			return int(str(num)+str(10-initial))
