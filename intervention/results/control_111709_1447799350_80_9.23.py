def num_common_letters(goal_word, guess):
	count, letters = 0, []
	for x in goal_word: #checks each letter in each word
		for y in guess:
			if x==y and x not in letters:  #checks for repeats
				count = count + 1
				letters.append(x)
	return count