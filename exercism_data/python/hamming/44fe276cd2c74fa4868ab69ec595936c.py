def hamming(x, y):
	return sum(map(lambda pair: pair[0] != pair[1] , zip(x,y))) + abs(len(x) - len(y))
