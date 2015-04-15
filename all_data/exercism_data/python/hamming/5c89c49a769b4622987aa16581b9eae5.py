def distance(a, b):
	count = 0
	for s1, s2 in zip(a, b):
		if s1 != s2:
			count += 1

	return count
