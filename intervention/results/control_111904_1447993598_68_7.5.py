def num_common_letters(goal_word, guess):
	common = []
	for x in list(goal_word):
		if x not in common and x in list(guess):
			common += [x]
	return len(common)