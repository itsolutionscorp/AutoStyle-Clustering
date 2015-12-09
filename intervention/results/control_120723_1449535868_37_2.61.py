def num_common_letters(goal_word, g):
	return len([word for word in set(goal_word) if word in g])