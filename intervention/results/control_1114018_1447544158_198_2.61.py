def num_common_letters(goal_word, guess):
	return len(set([char for char in goal_word if char in guess]))