def num_common_letters(goal_word, guess):
	goal_word = sorted(list(''.join(set(goal_word))))
	guess = sorted(list(''.join(set(guess))))
	repeat_list, common_list = [], []
	for i in range(max(len(goal_word),len(guess))):
		if goal_word[i] in guess and goal_word[i] not in repeat_list:
			common_list.append(goal_word[i])
		repeat_list.append(goal_word[i])
	return len(common_list)