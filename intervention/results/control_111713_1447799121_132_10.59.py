def num_common_letters(goal_word, guess):
	let = []
	count = 0
	for l in goal_word:
		for g in guess:
			if l == g and l not in let:
				let.append(l)
				count += 1
	return count