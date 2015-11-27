def num_common_letters(goal_word, guess):
	x, y, counter = 0, 0, 0
	while y < len(guess) and x < len(goal_word):
		if goal_word[x] == guess[y]:
			counter, y, x = (counter + 1), (y + 1), 0
		else:
			x += 1
	return counter