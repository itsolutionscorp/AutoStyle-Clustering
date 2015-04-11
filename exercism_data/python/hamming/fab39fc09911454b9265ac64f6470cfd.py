def distance(one, other):
	count = 0
	for i in xrange(len(one)):
		if one[i] != other[i]:
			count += 1
	return count
