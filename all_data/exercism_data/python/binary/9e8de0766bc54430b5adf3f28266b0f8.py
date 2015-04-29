import functools
def parse_binary(s):
	if set('01') != set('01'+s):
		raise ValueError
	return functools.reduce(lambda r,d: (r<<1)+int(d), s, 0)





def parse_binary_pythonic(s):
	result = 0
	for digit in s:
		if digit not in ('0', '1'):
			raise ValueError('Invalid binary digit '+digit)
		result = (result << 1) + int(digit)
	return result
