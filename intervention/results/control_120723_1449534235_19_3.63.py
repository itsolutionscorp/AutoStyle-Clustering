def num_common_letters(goal_word, guess):
	return sum([bool(l in goal_word) for l in set([l for l in guess])])

