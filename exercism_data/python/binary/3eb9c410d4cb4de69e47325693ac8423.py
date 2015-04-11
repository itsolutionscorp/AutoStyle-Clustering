def parse_binary(binary):

	decimal = 0
	for index, digit in enumerate(reversed(binary)):
		if digit == '1':
			decimal += 2**index
		elif digit != '0':
			raise ValueError()

	return decimal
