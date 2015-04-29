def distance(first, second):
	length = len(first)
	if length != len(second):
		raise ValueError
	return sum(1 for i in xrange(length) if first[i] != second[i])
