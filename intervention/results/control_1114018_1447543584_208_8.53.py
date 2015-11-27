def num_common_letters(goal_word, guess):
	common_letters = ""
	for char in goal_word:
		if not char in common_letters:
			if char in guess:
				common_letters += char
	return len(common_letters)