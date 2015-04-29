class Luhn(object):

	def __init__(self, num):
		self.digits = list(int(x) for x in str(num))


	def addends(self):
		output = self.digits[:]
		for i,digit in enumerate(output):
			if (len(output)-i)%2 == 0:
				output[i] = digit*2
				if output[i]>10:
					output[i] = output[i]-9
		return output

	def checksum(self):
		return sum(x for x in self.addends())%10

	def is_valid(self):
		return self.checksum()==0

	@classmethod
	def create(cls,incomplete):
		#simplified create method by using class methods above
		temp = Luhn(incomplete*10).checksum()
		output = incomplete*10+(10-temp)%10
		return output
