def num_common_letters(goal_word, g):
	letters = [word for word in set(goal_word) if word in g]
	return len(letters)