def num_common_letters(goal_word, guess):
	goal = set(goal_word)
	return len([i for i in goal if i in guess])
