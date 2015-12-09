def num_common_letters(goal_word, guess):
	cache = set('')
	for i in goal_word:
		if i in guess:
			cache.add(i)
	return len(cache)
