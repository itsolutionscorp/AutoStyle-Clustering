class Luhn(object):
	"""Represents the Luhn checksum of the given number"""
	def __init__(self,n):
		self.number = n

	def digits(self):
		"""Separates the digits of the number"""
		digits = []
		n = self.number
		while n > 0:
			n,m = divmod(n,10)
			digits = [m] + digits
		return digits

	def addends(self):
		"""Doubles every other digit, subtracting 9 from those over 9"""
		digits = self.digits()
		for i in range(len(digits)-2,-1,-2):
			digits[i] *= 2
			if digits[i] > 9:
				digits[i] -= 9
		return digits

	def checksum(self):
		"""Calculates the Luhn checksum"""
		return sum(self.addends()) % 10

	def is_valid(self):
		"""Determines whether or not this number is a valid Luhn number"""
		return self.checksum() == 0

	@classmethod
	def create(cls,n):
		"""Creates a Luhn number from the given partial number."""
		first_try = cls(n * 10)
		return first_try.number + ((-first_try.checksum()) % 10)
