def num_common_letters(goal_word, guess):
	counter=0
	if guess in goal_word:
		return 1
	for j in range (0,len(goal_word)):
		for i in range (0,len(guess)):
			if guess[i] == goal_word[j]:
				counter+=1
	return counter