from operator import mul
from functools import reduce

def slices(numbers, size):
	nlen = len(numbers)
	if (size > nlen):
		raise ValueError('Slice size too large')
	n = [int(s) for s in numbers]
	return [n[i : i + size] for i in range(0, nlen - size + 1)]

def largest_product(numbers, size):
	if (size > len(numbers)):
		raise ValueError('Slice size too large')
	return max([reduce(mul, l, 1) for l in slices(numbers, size)])
