def num_common_letters(goal_word, guess):
	count, lst = 0, []
	for letter in goal_word:
		if not letter in lst:
			if letter in guess:
				count += 1
				lst += letter
	return count