def num_common_letters(goal_word, guess):
	count = 0
	used_letter = []
	for letter in goal_word:
		if letter in guess and letter not in used_letter:
			count+=1
		used_letter+=[letter]
	return count
	
