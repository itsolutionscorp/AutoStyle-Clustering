class Luhn(object):
	def __init__(self, number):
		super(Luhn, self).__init__()
		self.number = number

		digits = []
		while number > 0:
			digits.append(number % 10)
			number /= 10

		for i in range(1, len(digits), 2):
			digits[i] *= 2
			if digits[i] > 9:
				digits[i] -= 9

		digits.reverse()
		self.digits = digits
	
	def addends(self):
		return self.digits

	def checksum(self):
		return sum(self.digits) % 10

	def is_valid(self):
		return self.checksum() == 0

	@staticmethod
	def create(number):
		t = Luhn(number*10)
		cs = t.checksum()
		return t.number + ((10 - cs) % 10)
