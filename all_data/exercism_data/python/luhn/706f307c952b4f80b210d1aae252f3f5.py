class Luhn:
	def __init__(self, n):
		self.number = n

	def is_valid(self):
		return self.checksum() == 0

	def checksum(self, number=None):
		number = number or self.number
		return sum(self.addends()) % 10 

	@staticmethod
	def create(n):
		num = Luhn(n*10)
		return num.number + -num.checksum() % 10
		

	@staticmethod
	def check_digit((pos, d)):
		s = d * 2 if pos % 2 else d
		return s % 10 + s / 10

	@staticmethod
	def luhn_addends(number):
		digits = map(int, str(number))
		return map(Luhn.check_digit, enumerate(digits, len(digits)+1))

	def addends(self):
		return Luhn.luhn_addends(self.number)
