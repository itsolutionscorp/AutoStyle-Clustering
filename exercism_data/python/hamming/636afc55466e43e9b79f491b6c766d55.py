def distance(s1, s2):
	return sum(x != y for x, y in map(None, s1, s2))
