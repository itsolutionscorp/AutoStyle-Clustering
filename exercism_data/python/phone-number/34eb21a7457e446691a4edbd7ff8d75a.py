from string import digits

PHONE_NUMBER_LENGTH = 10

class Phone(object):
	def __init__(self, num_str):
		self.number = ''.join(c for c in num_str if c in digits)
		if len(self.number) == PHONE_NUMBER_LENGTH + 1 and self.number[0] == '1':
			self.number = self.number[1:]
		if len(self.number) != PHONE_NUMBER_LENGTH:
			self.number = '0' * PHONE_NUMBER_LENGTH

	def pretty(self):
		return '({}) {}-{}'.format(self.area_code(), self.number[3:6], self.number[6:])

	def area_code(self):
		return self.number[0:3]
