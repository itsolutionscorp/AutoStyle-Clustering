def hamming(x, y):
	result = 0
	length = min(len(x), len(y))
	for i in xrange(0, length):
		if x[i] != y[i]:
			result += 1
	result += abs(len(x) - len(y))
	return result
