def num_common_letters(goal_word, guess):
	common = []
	for x in list(guess):
		if x in list(goal_word) and x not in common:
			common += [x]
	return len(common)