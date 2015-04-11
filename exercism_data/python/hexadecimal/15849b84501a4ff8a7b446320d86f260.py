from string import hexdigits

def hexa(hex_num):
	hexs = (hexdigits.index(c.lower()) for c in hex_num)
	return reduce(lambda dec, n: dec * 16 + n, hexs)
