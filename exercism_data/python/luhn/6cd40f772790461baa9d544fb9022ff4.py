class Luhn(object):

	@staticmethod
	def create(number):
		o = Luhn(number*10)
		return o.number + (10 - o.checksum()) % 10

	def __init__(self, number):
		self.number = number

	def addends(self):
		for index, digit in enumerate(reversed(str(self.number))):
			digit = int(digit)
			if index % 2 == 0:
				yield digit
			else:
				digit = digit * 2
				if digit > 9:
					yield digit - 9
				else:
					yield digit

	def checksum(self):
		return sum(self.addends()) % 10

	def is_valid(self):
		return self.checksum() == 0
