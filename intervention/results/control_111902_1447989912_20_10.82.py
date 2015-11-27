def num_common_letters(goal_word, guess):
	common = 0
	same = []
	for x in range (0, len(goal_word)):
		for y in range (0, len(guess)):
			if guess[y] not in same and goal_word[x] == guess[y]:
				same.append(guess[y])
				common += 1
	return common