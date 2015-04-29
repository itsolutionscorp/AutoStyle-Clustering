#	0, 1, 2, 3, 4, 5, 6, 7, 8, 9
evens = {i:i for i in xrange(10)}

#	0, 2, 4, 6, 8, 1, 3, 5, 7, 9
odds  = {i:int(c) for i, c in enumerate("0246813579")}

xform = (evens, odds)

class Luhn(object):

	@staticmethod
	def create(number):
		return number*10 + (10 - Luhn(number*10).checksum()) % 10

	def __init__(self, number):
		self.number = number

	def addends(self):
		digits = map(int, str(self.number))
		return [
			xform[index & 1][digit]
			for index, digit in enumerate(reversed(digits))
		]

	def checksum(self):
		return sum(self.addends()) % 10

	def is_valid(self):
		return self.checksum() == 0
