def slices(series, length):
	if not length > 0:
		raise ValueError('Length needs to be greater than 0.')

	if length > len(series):
		raise ValueError('Length cannot be longer than series length.')

	processed_series = list(map(int, list(series)))
	return [
		processed_series[index:index + length]
		for index in xrange(len(processed_series) - length + 1)
	]
