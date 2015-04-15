class Octal(object):
	def __init__(self, o_str):
		for c in o_str:
			if c not in '01234567':
				raise ValueError("Invalid octal digit: {}".format(c))
		self.o_str = o_str

	def to_decimal(self):
		val = 0
		for c in self.o_str:
			val = val * 8 + int(c)
		return val
