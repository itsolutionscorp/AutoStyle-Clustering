def distance(a, b):
	if not len(a) == len(b):
		raise ValueError("length of strings are not equal")
	return sum([a[i] != b[i] for i in xrange(len(a))])
