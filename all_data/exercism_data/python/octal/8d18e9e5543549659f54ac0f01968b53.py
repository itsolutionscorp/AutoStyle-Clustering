from string import octdigits

class Octal(object):
	def __init__(self, octal):
		self.digits = []
		for c in octal:
			Octal._validate_digit(c)
			self.digits.append(int(c))

	@staticmethod
	def _validate_digit(d):
		if d not in octdigits:
			raise ValueError("Invalid octal digit: {}".format(d))

	def to_decimal(self):
		return reduce(lambda x,y: 8*x + y, self.digits)
