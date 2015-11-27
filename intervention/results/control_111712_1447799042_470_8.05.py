def num_common_letters(goal_word, guess):
	letters = []
	for char in goal_word:
		for char2 in guess:
			if char == char2 and char not in letters:
				letters.append(char)
	return len(letters)
