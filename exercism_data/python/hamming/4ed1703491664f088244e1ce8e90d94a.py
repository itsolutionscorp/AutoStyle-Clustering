def distance(x, y):
	if len(x) != len(y):
		return 0
	mismatches = [i for i in xrange(len(x)) if x[i] != y[i]]
	return len(mismatches)
