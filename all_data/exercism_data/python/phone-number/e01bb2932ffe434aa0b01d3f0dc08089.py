import re

class Phone:
	def __init__(self, phone):
		phone = re.sub(r'[^0-9]', '', phone)
		if len(phone) == 11 and phone[0:1] == '1':
			phone = phone[1:]
		elif len(phone) != 10:
			phone = '0000000000'
		self.number = phone

	def area_code(self):
		return self.number[0:3]

	def pretty(self):
		return '({0}) {1}-{2}'.format(self.number[0:3], self.number[3:6], self.number[6:])
