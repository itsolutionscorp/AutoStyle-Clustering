def num_common_letters(goal_word, guess):
	lst = []
	c = 0
	for x in goal_word:
		if x not in lst:
			lst.append(x)
	for x in guess:
		if x in lst:
			lst.remove(x)
			c += 1
	return c
