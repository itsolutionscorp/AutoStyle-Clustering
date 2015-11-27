def num_common_letters(goal_word, guess):
	goal_word_characters = [n for n in goal_word]
	repeats = []
	for n in guess:
		if n in goal_word_characters and n not in repeats:
			repeats.append(n)
	return len(repeats)