from operator import mul

def slices(series, num):
	"""Returns all possible consecutive number series of length `num` in a string of digits

	Keyword arguments:
	series -- string of digits
	num -- length of substrings to return
	"""
	if num > len(series) or num < 1:
		raise ValueError
	if not series.isdigit():
		raise TypeError("Input string must consist only of digits!")
	return [[int(x) for x in series[index:index+num]] for index in xrange(len(series) + 1 - num)]

def largest_product(series, length):
	"""Returns the largest product of subseries of given length from series of digits

	Keyword arguments:
	series -- string of digits
	length -- length of subseries
	"""
	if length > len(series):
		raise ValueError
	if length == 0 and len(series)==0:
		return 1
	return max((reduce(mul,s) for s in slices(series,length)))
