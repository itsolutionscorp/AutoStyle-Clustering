def distance(a,b):
	return sum([x[0]!=x[1] for x in zip(a,b)])
