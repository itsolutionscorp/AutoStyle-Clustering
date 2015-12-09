def num_common_letters(goal_word, guess):
	return len(''.join(set(goal_word).intersection(set(guess))))