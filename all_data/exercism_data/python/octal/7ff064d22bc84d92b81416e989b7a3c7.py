import re

class Octal:
	def __init__(self, str):
		m = re.search(r"([^0-7])", str)
		if m:
			raise ValueError("Invalid octal digit: {0}".format(m.group(0)))
		self.str = str

	def to_decimal(self):
		out = 0
		for num in list(self.str):
			out = out * 8 + int(num)
		return out
