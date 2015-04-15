def distance(s1, s2):
	return sum(x != y for x, y in zip(s1, s2))
