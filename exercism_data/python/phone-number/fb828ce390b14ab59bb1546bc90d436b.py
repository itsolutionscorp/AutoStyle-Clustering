class Phone(object):

	def __init__(self, number):

		#	remove any non-digits
		number = ''.join([x for x in number if x.isdigit()])

		self.number = '0000000000'

		length = len(number)
		if length == 10:
			self.number = number

		elif length == 11:
			if number[0] == '1':
				self.number = number[1:]

	def area_code(self):
		return self.number[:3]

	def pretty(self):
		return "(%s) %s-%s" % (self.number[:3], self.number[3:6], self.number[6:])
