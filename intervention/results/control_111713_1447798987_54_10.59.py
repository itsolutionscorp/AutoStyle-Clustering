def num_common_letters(goal_word, guess):
	letts = []
	count = 0
	for letter in goal_word:
		for gletter in guess:
			if letter == gletter and letter not in letts:
				letts.append(letter)
				count += 1
	return count