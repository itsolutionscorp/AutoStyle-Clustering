def num_common_letters(goal_word, guess):
	goal_word = sorted(list(''.join(set(goal_word))))
	guess = sorted(list(''.join(set(guess))))
	repeatList = []
	commonList = []
	length = max(len(goal_word),len(guess))
	for i in range(length):
		if goal_word[i] in guess and goal_word[i] not in repeatList:
			commonList.append(goal_word[i])
		repeatList.append(goal_word[i])
	return len(commonList)