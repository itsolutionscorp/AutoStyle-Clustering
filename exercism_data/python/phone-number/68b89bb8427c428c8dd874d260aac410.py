import re

class Phone:
	def __init__(self, digits):
		self.number = self.clean(digits)

	def clean(self, digits):
		digits = re.sub("[\D]", "", digits)
		if len(digits) > 10 and not digits.startswith('1') or len(digits) < 10:
			digits = "0000000000"
		elif len(digits) > 10 and digits.startswith('1'):
			digits = digits[1:]
		return digits 
	
	def area_code(self):
		return self.number[:3]

	def pretty(self):
		area_code = self.area_code()
		start = self.number[3:6]
		end = self.number[6:]
		return ("(%s) %s-%s") % (area_code, start, end)
