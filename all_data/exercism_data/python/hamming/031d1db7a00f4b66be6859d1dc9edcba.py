def distance(one, two):
	return sum([0 if first == second else 1 for first, second in zip(one,two)])
