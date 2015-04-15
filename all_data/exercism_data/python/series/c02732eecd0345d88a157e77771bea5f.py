def slices(digits, n):
	if n <= 0 or n > len(digits): raise ValueError()
	return [map(int, list(digits[i:i+n])) for i in xrange(0, len(digits)-n+1)]
