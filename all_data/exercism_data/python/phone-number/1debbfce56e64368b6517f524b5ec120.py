from string import digits

class Phone():
	def __init__(self, number):
		self.number = ''.join(d for d in number if d in digits)
		if len(self.number) == 11 and self.number[0] == '1':
			self.number = self.number[1:]
		elif len(self.number) != 10:
			self.number = "0000000000"

	def area_code(self):
		return self.number[:3]

	def pretty(self):
		return '({}) {}-{}'.format(self.number[:3], self.number[3:6], self.number[6:])
