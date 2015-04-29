digits = {0: 0, 1: 2, 2: 4, 3: 6, 4: 8, 5: 1, 6: 3, 7: 5, 8: 7, 9: 9}

class Luhn:

	def __init__(self, *num):
		self.number = num[0] if num else 0
		self.numlist = [int(i) for i in str(num[0])] if num else [0]

	def addends(self):
		numout = self.numlist
		for i in range(len(numout)):
			if (i%2 and len(numout)%2) or (not i%2 and not len(numout)%2):
				numout[i] = digits[numout[i]]
		return numout

	def checksum(self):
		return sum(self.addends()) % 10

	def is_valid(self):
		return not self.checksum()

	def create(self, num):
		new_digit = (10 - Luhn(num*10).checksum()) % 10
		return int(str(num) + str(new_digit))
