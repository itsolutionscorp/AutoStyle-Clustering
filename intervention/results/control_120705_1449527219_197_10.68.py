def num_common_letters(goal_word, guess):
	tot=0
	if guess in goal_word:
		return 1
	for i in range (0,len(goal_word)):
		for j in range (0,len(guess)):
			if guess[j] == goal_word[i]:
				tot+=1
	return tot
