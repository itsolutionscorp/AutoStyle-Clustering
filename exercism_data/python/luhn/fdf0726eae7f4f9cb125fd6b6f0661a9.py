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
		complete = []
		for i,digit in enumerate(str(incomplete)):
			newDigit = int(digit)
			if (len(str(incomplete))-i)%2 == 1:
				newDigit *= 2
				if newDigit>10:
					newDigit -= 9
			complete.append(newDigit)
		check = sum(x for x in complete)%10
		output = str(incomplete)+str((10-check)%10)
		output = int(output)
		return output
