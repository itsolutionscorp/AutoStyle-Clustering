def num_common_letters(goal_word, guess):
	goal_word_characters = [n for n in goal_word]
	repeats = []
	count = 0
	for n in guess:
		if n in goal_word_characters and n not in repeats:
			count += 1
			repeats.append(n)
	return count
			