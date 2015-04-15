from math import log10

def make_digits(number):
		digits = [int(number / 10**i) % 10 for i in range(int(log10(number))+1)]
		for i in range(len(digits)):
			if i % 2 == 1:
				digits[i] *= 2
				if digits[i] > 10:
					digits[i] -= 9
		digits.reverse()
		return digits


class Luhn:
	@staticmethod
	def create(number):
		digits = make_digits(number*10)
		check = sum(digits) % 10
		if check != 0:
			return number * 10 + (10 - check)
		else:
			return number * 10
	
	def __init__(self, number):
		self.input = number
		self.digits = make_digits(number)


	def addends(self):
		return make_digits(self.input)
	
	def checksum(self):
		total = sum(self.digits)
		return total % 10
	
	def is_valid(self):
		return self.checksum() == 0
