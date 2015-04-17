def slices(source, n):
	if n == 0:
		raise ValueError('Overly short slice')
	if n > len(source):
		raise ValueError('Overly long slice')
	converted = [int(l) for l in source]
	return [converted[i : i + n] for i in xrange(0, len(source) - n + 1)]
