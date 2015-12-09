def num_common_letters(goal_word, guess):
	goal_letters = []
	count = 0
	for x in goal_word:
		if x not in goal_letters:
			goal_letters.append(x)
	for x in guess:
		if x in goal_letters:
			goal_letters.remove(x)
			count += 1
	return count

