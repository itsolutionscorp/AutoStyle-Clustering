class Luhn(object):
	def __init__(self, number):
		self.number = str(number)
		
	def addends(self):
		ends = [int(n) for n in self.number][::-1]
		for i in range(1, len(ends), 2):
			if ends[i] * 2 > 10:
				ends[i] = ends[i] * 2 - 9
			else:
				ends[i] = ends[i] * 2
		return ends[::-1]
	def checksum(self):
		return sum(self.addends()) % 10
	def is_valid(self):
		return self.checksum() == 0
	
	@staticmethod
	def create(num):
		chksm = Luhn(int(str(num)+'0')).checksum()
		if chksm == 0:
			return int(str(num)+'0')
		else:
			return int(str(num)+str(10 - chksm))
