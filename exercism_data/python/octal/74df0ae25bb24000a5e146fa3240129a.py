class Octal:
	def __init__(self, value):
		self.value = value
		
		for v in self.value:
			if not (v in "01234567"):
				raise ValueError("Invalid octal digit: {}".format(v))
		
	def to_decimal(self):
		valLen = len(self.value)
		
		return sum(int(self.value[n]) * pow(8, valLen - n - 1) for n in range(valLen))
