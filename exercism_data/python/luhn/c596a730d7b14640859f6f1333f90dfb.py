from collections import deque
class Luhn():
	def __init__(self, num = None):
		self.digits = [int(x) for x in str(num) if num]

	def addends(self):
		digits = self.digits[:]
		retval = deque()
		b = 0
		try:
			while True:
				d = digits.pop()
				if b:
					retval.appendleft(2*d) if 2*d < 10 else retval.appendleft(2*d - 9)
				else:
					retval.appendleft(d)
				b ^= 1
		except IndexError:
			pass
		return retval

	def checksum(self):
		return sum(self.addends())%10

	def is_valid(self):
		return not self.checksum()

	@staticmethod
	def create(num):
		if not Luhn(num*10).is_valid():
			return num*10 + 10 - Luhn(num*10).checksum()
		return num*10
