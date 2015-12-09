def num_common_letters(goal_word, guess):
	num = 0
	st2 = set([letter for letter in guess])
	for letter in st2:
		if letter in goal_word:
			num += 1
	return num