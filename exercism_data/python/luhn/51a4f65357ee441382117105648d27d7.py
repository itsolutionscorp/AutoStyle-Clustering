def intJoin(x):
	return int(''.join(map(str,x)))
class Luhn:
	def __init__(self,n):
		self.startNum = [int(i) for i in str(n)]
		self.reverseNum = [int(i) for i in str(n).replace(" ","").strip()][::-1]
		self.len = len(self.startNum)
		self.num = [(2**(i%2)*self.reverseNum[i]) - 9 * (i%2)*(2*self.reverseNum[i] >=10) for i in range(self.len)][::-1]
	def checksum(self):
		return sum(self.num)%10
	def is_valid(self):
		return True if self.checksum() == 0 else False
	def addends(self):
		return self.num #+ [(10-sum(self.num))%10]
	@classmethod
	def create(cls,n):
		if cls(n).is_valid(): return n
		else:
			temp = cls(intJoin([int(i) for i in str(n)]+[0]))
			buffed = (10 - temp.checksum()) % 10
			return intJoin(temp.startNum[:-1]+[buffed])
