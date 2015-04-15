import re
import string

class Phone:
	def __init__(self, number):

		digits = "".join(x for x in number if x not in string.punctuation)
		digits = "".join(digits.split())

		if len(digits) > 10 and not digits.startswith('1') or len(digits) < 10:
			self.number = "0000000000"
		elif len(digits) > 10 and digits.startswith('1'):
			match = re.match('[\d]{10}', digits[1:])
			self.number = match.group()
		else:
			match = re.match('[\d]{10}', digits)
			self.number = match.group()
	
	def area_code(self):
		return self.number[0:3]

	def pretty(self):
		area_code = self.area_code()
		start = self.number[3:6]
		end = self.number[6:]
		return ("(%s) %s-%s") % (area_code, start, end)
