def distance(strand1, strand2):
	count = 0
	for c1, c2 in zip(strand1, strand2):
		if c1 != c2:
			count += 1
	return count
