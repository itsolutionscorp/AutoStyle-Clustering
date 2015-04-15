import re
class Phone(object):
	def __init__(self, phone):
		number = re.sub(r'[ .()-]', '', phone)
		valid_phone_number = re.compile('^1?(\d{10})$')
		result = valid_phone_number.match(number)
		if (result):
			self.number = result.group(1)
			print self.number
		else:
			self.number = '0000000000'

	def area_code(self):
		area_pattern = re.compile('^\d{3}');
		return area_pattern.match(self.number).group()

	def pretty(self):
		print self.number
		return re.sub(r'^1?(\d{3})(\d{3})(\d{4})$', r'(\1) \2-\3', self.number)
