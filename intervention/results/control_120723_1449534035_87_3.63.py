
def num_common_letters(goal_word, guess):
	return sum([bool(letter in goal_word) for letter in set([letter for letter in guess])])