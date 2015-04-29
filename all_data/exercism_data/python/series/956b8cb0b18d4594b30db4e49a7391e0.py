def slices(digits, n):
	if n > 0 and n <= len(digits): return [map(int, list(digits[i:i+n])) for i in xrange(0, len(digits)-n+1)]
	raise ValueError()
