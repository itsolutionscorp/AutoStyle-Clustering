def distance(base, copy):
	return sum(1 if base[i] != copy[i] else 0 for i in range(0, len(base)))
