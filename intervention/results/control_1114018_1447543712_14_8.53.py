def num_common_letters(goal_word, guess):
	common_letters = ""
	for character in goal_word:
		if not character in common_letters:
			if character in guess:
				common_letters += character
	return len(common_letters)