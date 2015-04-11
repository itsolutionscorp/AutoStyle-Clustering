class Luhn:
	def __init__(self,digits):
		self.digits = digits
		
	@staticmethod
	def create(digits):
		b=Luhn(digits*10).checksum()
		return int(str(digits)+str((10-b)%10))
		
	def is_valid(self):
		if self.checksum() ==0:
			return True
		else:
			return False
			
	def checksum(self,):
		return sum(self.addends())%10
		
	def addends(self):
		invers=str(self.digits)[::-1]
		new=[]
		for k in range(0,len(invers)):
			if k % 2 == 0:
				new.append(int(invers[k]))
			else:
				if 2*int(invers[k])>9:
					new.append(2*int(invers[k])-9)
				else:
					new.append(2*int(invers[k]))
				
				
		return new[::-1]
				
		
