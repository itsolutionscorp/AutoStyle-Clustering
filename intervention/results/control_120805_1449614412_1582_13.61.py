def num_common_letters(goal_word, guess):
	w1, g2 = list(goal_word), list(guess)
	total, seen = 0, []
	if not w1:
		return total
	for l1 in w1:
		for l2 in g2:
			if l1 is l2 and l1 not in seen:
				total += 1
				seen.append(l1)
				g2.remove(l2)
	return total
