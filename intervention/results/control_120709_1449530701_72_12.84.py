def num_common_letters(goal_word, guess):
	letters = []
	count = 0
	for x in goal_word:
		if x not in letters:
			letters.append(x)
	for g in guess:
		if g in letters:
			letters.remove(g)
			count += 1
	return count
