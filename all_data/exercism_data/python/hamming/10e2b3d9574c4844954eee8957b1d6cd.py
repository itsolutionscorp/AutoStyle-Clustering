def distance(original,comparator):
	return len([a for a,b in zip(original,comparator) if a != b])
