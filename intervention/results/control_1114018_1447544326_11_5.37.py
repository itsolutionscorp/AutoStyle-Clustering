def num_common_letters(goal_word, guess):
	if len(goal_word):
		return (goal_word[0] in guess) + num_common_letters(goal_word[1:],guess)
	else:
		return 0