def num_common_letters(goal_word, guess):
	count = 0
	letters = []
	for x in goal_word:
		for y in guess:
			if x==y and x not in letters:
				count+=1
				letters.append(x)
	return count