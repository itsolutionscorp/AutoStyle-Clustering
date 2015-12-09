def num_common_letters(goal_word, guess):
	num = len([l for l in set(list(goal_word)) if l in set(list(guess))])
	return num