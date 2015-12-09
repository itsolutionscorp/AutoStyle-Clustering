def num_common_letters(goal_word, guess):
	goal_word = sorted(list(''.join(set(goal_word))))
	guess = sorted(list(''.join(set(guess))))
	word_dict = {}
	repeat_list = []
	common_list = []
	word_dict[len(goal_word)], word_dict[len(guess)] = goal_word, guess
	shorterString = word_dict[min(len(goal_word),len(guess))]
	longerString = word_dict[max(len(goal_word),len(guess))]
	
	for i in range(min(len(goal_word),len(guess))):
		if shorterString[i] in longerString and shorterString[i] not in repeat_list:
			common_list.append(shorterString[i])
		repeat_list.append(shorterString[i])
	return len(common_list)