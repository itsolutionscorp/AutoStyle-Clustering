def num_common_letters(goal_word, guess):
	seen = []
	for letter in guess:
		if letter in goal_word and letter not in seen:
			seen.append(letter)
	return len(seen)