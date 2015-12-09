def num_common_letters(goal_word, guess):
	return len(''.join(sorted(set(goal_word) & set(guess))))