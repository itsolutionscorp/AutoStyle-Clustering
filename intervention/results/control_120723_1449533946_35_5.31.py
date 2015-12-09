def num_common_letters(goal_word, guess):
	num = 0
	st2 = set([letter for letter in guess])
	return sum([bool(letter in goal_word) for letter in st2])