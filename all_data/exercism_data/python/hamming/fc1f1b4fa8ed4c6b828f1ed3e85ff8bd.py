def hamming(a, b):
	count = 0
	for i in xrange(min(len(a), len(b))):
		if a[i] != b[i]:
			count += 1
	
	count += abs(len(a) - len(b))
	return count
