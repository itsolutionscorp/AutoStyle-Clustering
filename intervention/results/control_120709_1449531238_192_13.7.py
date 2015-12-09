def num_common_letters(goal_word, guess):
	letters = [] 
	count = 0
	for x in goal_word: 
		if x not in letters:
			letters.append(x)
	for x in guess: 
		if x in letters:
			letters.remove(x)
			count += 1
	return count

