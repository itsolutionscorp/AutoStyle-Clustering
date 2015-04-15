def hexa(num):
	hex = '0123456789abcdef'
	res = 0
	for power,value in enumerate(num[::-1].lower()):
		if value not in hex:
			raise ValueError('not a valid hex input')
		res += hex.index(value) * (16**power)
	return res
