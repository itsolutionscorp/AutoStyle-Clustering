def largest_product(s, n):
	return reduce(
		lambda result, one_slice: max(result, _list_product(one_slice)),
		_gen_slices(s, n),
		0
	)

def slices(s, n):
	if n <= 0:
		raise ValueError('Slices of zero length do not make sense!')
	return list(_gen_slices(s, n))

def _gen_slices(s, n):
	""" Generate a sequence of slices of length `n` from a string `s`
	"""
	if n > len(s):
		raise ValueError('Slice length can not exceed length of the string to slice')
	s = map(int, s)
	for i in range(len(s) - n + 1):
		yield s[i:i+n]

def _list_product(l):
	""" Multiply all numbers in a sequence
	"""
	return reduce(lambda result,i: result*i, l, 1)
