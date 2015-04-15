def parse_octal(octal):
	if ( not octal.isdigit() or any(int(x) > 7 for x in octal) ):
			raise ValueError("Error in parse_octal {}".format(octal))
	in_decimal = 0
	power = 0
	for n in reversed(octal):
		in_decimal += int(n) * (8 ** power)
		power += 1
	return in_decimal
