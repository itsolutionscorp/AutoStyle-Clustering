def num_common_letters(goal_word, guess):
	common = []
	for x in list(guess):
		if x not in common and x in list(goal_word):
			common += [x]
	return len(common)