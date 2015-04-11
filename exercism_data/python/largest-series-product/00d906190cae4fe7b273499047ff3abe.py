import operator

def _slice_iterator(digits, length):
	numbers = map(int, list(digits))
	return (numbers[i:i + length] for i in xrange(len(numbers) - length + 1))

def slices(digits, length):
	if len(digits) < length:
		raise ValueError('Invalid slice length.')
	return list(_slice_iterator(digits, length))

def largest_product(digits, length):
	return max(reduce(operator.mul, values, 1) for values in _slice_iterator(digits, length))
