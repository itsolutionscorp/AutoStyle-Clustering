def distance(s1, s2):
	return len(filter(lambda x: x[0] != x[1], zip(s1, s2)))
