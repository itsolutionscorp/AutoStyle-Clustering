def num_common_letters(goal_word, guess):
	count, used_letter = 0, []
	for letter in goal_word:
		if letter in guess and letter not in used_letter:
			count+=1
			used_letter+=[letter]
	return count