def num_common_letters(goal_word, guess):
	similar_letters = [character for character in goal_word if character in guess]
	distinct_letters = set(similar_letters)
	return len(distinct_letters)
