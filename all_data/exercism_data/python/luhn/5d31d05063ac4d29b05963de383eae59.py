def transform(i, d):
	if i % 2: # transform every 2nd character
		dd = d*2 
		return dd-9 if dd >=10 else dd
	else:
		return d
class Luhn:
	def __init__(self, digits):
		self.digits = digits
		self.luhndigits = self.addends()
		self.checksumdigits = self.checksum()

	def addends(self):
		result = []
		for i, d in enumerate(reversed(str(self.digits))):
			result.insert(0, transform(i, int(d)))
		return result	

	def checksum(self):
		return sum(self.luhndigits) % 10

	def is_valid(self):
		return not self.checksumdigits

	# @classmethod	
	# def create(cls, digits):
	# 	if len(str(digits)) % 2 == 0:
	# 		return int(str(digits)+'0')
	# 	else:
	# 		return 
