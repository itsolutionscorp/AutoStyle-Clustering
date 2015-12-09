def num_common_letters(goal_word, guess):
	count, usedLetter = 0, []
	for letter in goal_word:
		if letter in guess and letter not in usedLetter:
			count+=1
			usedLetter+=[letter]
	return count