def distance(strand1, strand2):
	"""Compare two DNA strands to identify hamming
	distance. This is equivalent of the minimum number
	of point mutations between two strands.
	"""
	dist = 0
	for x, y in zip(strand1, strand2):
		if (x != y):
			dist += 1

	return dist
