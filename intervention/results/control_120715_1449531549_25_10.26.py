def num_common_letters(goal_word, guess):
	used_letters = []
	num_of_same_letters = 0
	for letter in guess:
		if letter in goal_word and letter not in used_letters:
			num_of_same_letters += 1
			used_letters += letter
	return num_of_same_letters