def parse_binary(binary):

	decimal = 0
	for digit in binary:
		if int(digit) < 2:
			decimal = 2 * decimal + int(digit)
		else:
			raise ValueError()

	return decimal
