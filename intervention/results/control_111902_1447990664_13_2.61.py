def num_common_letters(goal_word, guess):
	return len([1 for g in set(guess) if goal_word.count(g)>0 ])