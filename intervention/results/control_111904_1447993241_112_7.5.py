def num_common_letters(goal_word, guess):
	common = []
	for char in list(guess):
		if char in list(goal_word) and char not in common:
			common += [char]
	return len(common)