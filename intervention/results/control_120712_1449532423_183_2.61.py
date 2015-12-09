def num_common_letters(goal_word, guess):
	return len(set([character for character in goal_word if character in guess]))
