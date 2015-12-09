def num_common_letters(goal_word, guess):
	return len(set([x for x in goal_word if x in guess]))
