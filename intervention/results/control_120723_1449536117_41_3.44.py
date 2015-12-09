def num_common_letters(goal_word, g):
	return len([word for word in set(g) if word in goal_word])

