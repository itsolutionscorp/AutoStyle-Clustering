from functools import reduce
import operator

def largest_product(series, length):
	return reduce(
				operator.mul, 
				max([sorted(x) for x in slices(series, length)]), 
				1)

def slices(series, length):
	'''
	Returns a list of lists that shows all ordered
	combinations a numerical series can be in, 
	given a length

	'''
	if len(series) < length:
		raise ValueError

	sliced = []

	for i in range(0, len(series) - length + 1):
		sliced.append(
			[int(c) for c in list(series[i:i+length])]
			)

	return sliced
