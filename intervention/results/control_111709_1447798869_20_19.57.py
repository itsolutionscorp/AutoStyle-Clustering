
def num_common_letters(goal_word, guess):
	letters1 = []
	letters2 = []
	count = 0
	for x in goal_word:
		if x not in letters1:
			letters1.append(x)
	for x in guess:
		if x not in letters2:
			letters2.append(x)
	for x in letters1:
		for y in letters2:
			if x==y:
				count+=1
	return count