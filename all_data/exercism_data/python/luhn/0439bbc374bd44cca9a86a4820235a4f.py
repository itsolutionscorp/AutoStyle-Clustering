class Luhn:
	def __init__(self, number):
		self.number = number
		self.digits = [int(c) for c in str(self.number)]
		for index in range(len(self.digits) - 2, -1, -2):
			self.digits[index] = self.__double(self.digits[index])

	def addends(self):
		return self.digits


	def checksum(self):
		return (sum(self.digits) % 10)

	@classmethod
	def create(self, number):
		l = Luhn(number * 10)
		c = l.checksum()
		return l.number + (0 if (c == 0) else 10 - c)

	def is_valid(self):
		return self.checksum() == 0

	def __double(self, value):
		d = value * 2
		return d if d < 10 else d - 9
