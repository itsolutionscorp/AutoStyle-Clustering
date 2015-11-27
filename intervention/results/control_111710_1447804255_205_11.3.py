def num_common_letters(goal_word, guess):
	count = 0
	lst = []
	for letter in goal_word:
		if letter in guess:
			if not letter in lst:
				count += 1
				lst += letter
	return count