def num_common_letters(goal_word, guess):
	goal_word = list(goal_word)
	guess = list(guess)
	common = []
	for char in guess:
		if char in goal_word and char not in common:
			common += [char]
	return len(common)
		
