def distance(x, y):
	if len(x) != len(y):
		return 0
	return sum([1 for i in xrange(len(x)) if x[i] != y[i]])
