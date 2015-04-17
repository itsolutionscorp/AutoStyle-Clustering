def slices(series, length):
	'''
	Returns a list of lists that shows all ordered
	combinations a numerical series can be in, 
	given a length
	'''
	if len(series) < length or length == 0:
		raise ValueError
	sliced = []
	for i in range(0, len(series) - length + 1):
		sliced.append(
			[int(c) for c in list(series[i:i+length])]
			)
	return sliced
