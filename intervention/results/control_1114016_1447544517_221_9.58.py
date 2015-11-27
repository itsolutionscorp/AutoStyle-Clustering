def num_common_letters(goal_word, guess):
	common_letters = []
	for letter in goal_word:
		if letter not in common_letters:
			for letters in guess:
				if letters not in common_letters and letters is letter:
					common_letters.append(letters)
	return len(common_letters)
