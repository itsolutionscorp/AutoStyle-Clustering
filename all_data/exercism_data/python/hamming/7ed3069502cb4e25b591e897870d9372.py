def distance(first, second):
	return sum(a != b for a,b in zip(first, second))
