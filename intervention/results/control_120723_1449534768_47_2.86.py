def num_common_letters(goal_word, guess):
	return len([l for l in set(goal_word) if l in set(guess)])
