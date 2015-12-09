def num_common_letters(goal, g):
	return len([letter for letter in set(goal) if letter in g])