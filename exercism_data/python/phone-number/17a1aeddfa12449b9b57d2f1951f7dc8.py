import re 


class Phone(object):
	def __init__(self, digits):
		self.digits = re.sub(r'[^\w]', r'', digits)
		self.number = self.clean_number()
	
	def clean_number(self):
		num = self.digits 
		if len(num) > 12 or len(num) < 10 or (len(num) == 11 and num[0] != '1'):
			num = "0000000000"
		if len(num) == 11:
			num = num[1:len(num)]
		return num

	def area_code(self):
		return self.number[:3]

	def pretty(self):
		num = self.number
		return "(%s) %s-%s" % (num[:3], num[3:6], num[6:10])
