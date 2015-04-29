class Luhn(object):
	def __init__(self, num=8888):
		self.num = num
		self.res = self.addends()
		self.should_be_0 = self.checksum()
		
	def addends(self):
		number = str(self.num)
		res = []
		for i,x in enumerate(number[::-1]):
			temp = int(x)
			if i%2 != 0:
				temp *= 2
			if temp > 9:
				temp -= 9
			res = [temp] + res
		return res
			
	def checksum(self):
		return sum(self.res)%10
		
	def is_valid(self):
		return self.should_be_0 == 0
	
	@staticmethod	
	def create(x):
		for i in range(10):
			number = int(str(x)+str(i))
			if Luhn(number).is_valid():
				return number
