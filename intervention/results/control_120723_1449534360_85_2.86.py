def num_common_letters(goal_word, g):
	return sum([bool(l in goal_word) for l in set([i for i in g])])