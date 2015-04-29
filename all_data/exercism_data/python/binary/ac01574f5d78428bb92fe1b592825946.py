def parse_binary(binary_string):
	'''convert binary string to decimal number'''
	if set(binary_string) - {'0','1'}:
		raise ValueError()
	return sum([2**ii for ii,bit in enumerate(binary_string[-1::-1]) if bit=='1'])
