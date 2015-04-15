digits = '0123456789abcdef'
valid_chars = set(digits)

values = {
	digit:index
	for index, digit in enumerate(digits)
}

def hexa(x):

	decimal = 0
	for digit in x.lower():
		decimal *= 16
		if digit in valid_chars:
			decimal += values[digit]
		else:
			raise ValueError()

	return decimal
