def num_common_letters(goal_word, guess):
	s_1 = set([l for l in guess])
	return sum([bool(l in goal_word) for l in s_1])
