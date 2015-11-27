def num_common_letters(goal_word, guess):
	goal_word_letters, guess = [goal_word[x] for x in range(0, len(goal_word))], [guess[x] for x in range(0, len(guess))]
	x, y, counter = 0, 0, 0
	while y < len(guess) and x < len(goal_word_letters):
		if goal_word_letters[x] == guess[y]:
			counter, y, x = (counter + 1), (y + 1), 0
		else:
			x += 1
	return counter