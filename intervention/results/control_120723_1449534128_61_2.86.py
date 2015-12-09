def num_common_letters(goal_word, guess):
	return sum([bool(l in goal_word) for l in set([i for i in guess])])
