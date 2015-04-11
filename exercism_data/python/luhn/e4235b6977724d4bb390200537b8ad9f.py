class Luhn(object):
	
	def __init__(self,number=0):
		self.num = number
		
	def checksum(self):
		return sum(self.addends())%10
		
	def is_valid(self):
		return self.checksum() % 10 == 0
		
	def addends(self):
		sNum = str(self.num)
		modNum = []
		for n in range(len(sNum)-1,-1,-1):
			if (len(sNum)-n)%2 == 0:
				modNum.append(self.mod9(int(sNum[n])*2))
			else:
				modNum.append(int(sNum[n]))
		modNum.reverse()
		return modNum
		
	def mod9(self,num):
		if num < 10:
			return num
		else:
			return num-9
	
	@staticmethod	
	def create(number):
		sNum = str(number)
		if Luhn(number).is_valid():
			return int(sNum)
		for i in range(0,10):
			if Luhn(int(sNum+str(i))).is_valid():
				return int(sNum+str(i))
