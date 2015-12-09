def num_common_letters(goal, g):
	return sum([bool(l in goal) for l in set([i for i in g])])
