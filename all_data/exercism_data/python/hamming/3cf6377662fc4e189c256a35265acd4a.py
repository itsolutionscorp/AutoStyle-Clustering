def distance(src, dst):
	return sum([s != d for (s,d) in zip(src,dst)])
