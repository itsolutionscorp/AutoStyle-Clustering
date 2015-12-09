def num_common_letters(goal_word, guess):
	cache = set('')
	counter = 0
	for i in goal_word:
		if i not in cache:
			cache.add(i)
	for j in guess:
		if j in cache:
			counter += 1
			cache.remove(j)
	return counter 
