def num_common_letters(goal_word, g):
	return len([i for i in set(goal_word) if g.find(i) != -1])