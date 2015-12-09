def num_common_letters(goal, g):
	return len([i for i in set(goal) if i in g])
