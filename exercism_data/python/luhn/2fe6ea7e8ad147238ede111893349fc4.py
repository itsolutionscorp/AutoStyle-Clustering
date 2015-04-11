class Luhn:
	@classmethod
	def create(cls, card_number):
		return cls(card_number * 10).fixnumber()
	def __init__(self, card_number):
		self.card_number = card_number
	def addends(self):
		digits = [int(n) for n in str(self.card_number)]				
		for i in range(-2, -len(digits) - 1, -2):			
			digits[i] *= 2
			if digits[i] >= 10:
				digits[i] -= 9		
		return digits
	def checksum(self):
		return sum(self.addends()) % 10
	def is_valid(self):
		return self.checksum() == 0
	def fixnumber(self):
		fixdigit = 0
		if self.checksum() > 0:
			fixdigit = 10 - self.checksum()		
		return self.card_number + fixdigit
	
