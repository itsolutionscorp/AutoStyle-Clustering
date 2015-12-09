def num_common_letters(goal, g):
	return len([l in goal for l in set([i for i in g])])