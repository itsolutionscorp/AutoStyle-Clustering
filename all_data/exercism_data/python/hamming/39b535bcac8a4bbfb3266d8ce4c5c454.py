def distance(x, y):
	return len([x for x, y in zip(x, y) if x != y])
