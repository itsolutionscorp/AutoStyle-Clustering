import re 


class Phone(object):
	def __init__(self, digits):
		self.number = self.__clean_number(digits)
	
	def __clean_number(self, digits):
		num = re.sub(r'[^\w]', r'', digits)
		if len(num) > 12 or len(num) < 10 or (len(num) == 11 and num[0] != '1'):
			num = "0000000000"
		if len(num) == 11:
			num = num[1:len(num)]
		return num

	def area_code(self):
		return self.number[:3]

	def pretty(self):
		return "(%s) %s-%s" % (self.number[:3], self.number[3:6], self.number[6:10])
