def num_common_letters(goal_word, guess):
	lst = []
	count = 0
	for x in goal_word:
		if x not in lst:
			lst.append(x)
	for x in guess:
		if x in lst:
			lst.remove(x)
			count += 1
	return count
