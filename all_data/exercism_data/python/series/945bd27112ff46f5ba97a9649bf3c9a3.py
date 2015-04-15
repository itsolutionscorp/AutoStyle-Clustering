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
	# Generate index and use it to generate lists of (int)
	return [[int(x) for x in series[index:index+num]] for index in xrange(len(series) + 1 - num)]
