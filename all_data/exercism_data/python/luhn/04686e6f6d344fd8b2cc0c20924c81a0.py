#/usr/bin/env python
class Luhn:
	def __init__(self, num=None):
		self.num = num
	def addends(self, num = None):
		if num == None: num = self.num
		digits = []
		for x in range(len(str(num))):
			x_digit = int(str(num)[x])
			if (not x % 2 and not len(str(num)) % 2) or (x % 2 and len(str(num)) % 2):
				if 2*x_digit < 10: 
					digits.append(2*x_digit)
				else: digits.append(2*x_digit-9)
			else:
				digits.append(x_digit)
		return digits
	def is_valid(self):
		if str(sum(self.addends())).endswith('0'): return True
		else: return False
	def checksum(self):
		return int(str(sum(self.addends()))[len(str(sum(self.addends())))-1])
	@staticmethod
	def create(num):
		adds = []
		for x in range(len(str(num))):
			x_digit = int(str(num)[x])
			if (not x % 2 and len(str(num)) % 2) or (x % 2 and not len(str(num)) % 2):
				if 2*x_digit < 10: 
					adds.append(2*x_digit)
				else: adds.append(2*x_digit-9)
			else:
				adds.append(x_digit)
		return int(str(num) + str(sum(adds)*9)[len(str(sum(adds)*9))-1])
