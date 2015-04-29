def distance(base, copy):
	return sum(x != y for (x,y) in zip(base, copy))
