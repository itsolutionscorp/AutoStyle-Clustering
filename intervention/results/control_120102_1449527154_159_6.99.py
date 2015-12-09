def num_common_letters(goal_word, guess):
	checked_letters = []
	for letter in guess:
		if letter in goal_word and letter not in checked_letters:
			checked_letters.append(letter)
	return len(checked_letters)
