def num_common_letters(goal_word, guess):
	common_letters = [character for character in goal_word if character in guess]
	return len(common_letters)