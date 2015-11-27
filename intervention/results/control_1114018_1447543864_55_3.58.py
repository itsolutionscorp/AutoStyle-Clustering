def num_common_letters(goal_word, guess):
	common_letters = [char for char in goal_word if char in guess]
	return len(common_letters)