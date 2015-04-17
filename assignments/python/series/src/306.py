def slices(s, n):
	if n <=0 or n > len(s):
		raise ValueError
	l = [int(c) for c in s]
	results = []
	for i in xrange(n, len(l)+1):
		results.append(l[i-n:i])
	return results
