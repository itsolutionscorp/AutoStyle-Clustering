def parse_binary(bits):
	if bits.strip('01'):
		raise ValueError('Input not binary.')
	return sum(1 << i for i,b in enumerate(reversed(bits))
	            if b == '1')
