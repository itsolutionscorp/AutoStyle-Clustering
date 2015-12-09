def num_common_letters(goal_word, guess):
	counter, checked_letters = 0, []
	for letter in guess:
		if letter in goal_word and letter not in checked_letters:
			counter += 1
			checked_letters.append(letter)
	return counter