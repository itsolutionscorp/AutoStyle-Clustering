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
	result = []
	start = 0
	while num <= len(series):
		result.append([int(x) for x in series[start:num]])
		start+=1
		num+=1
	return result
