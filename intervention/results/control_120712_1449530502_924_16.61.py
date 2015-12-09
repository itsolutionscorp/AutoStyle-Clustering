def num_common_letters(goal_word, guess):
	letters = []
	for letter in guess:
		letters.append(letter)
	

	similar_letters = []
	k = 0

	for character in goal_word:
		if character in letters and character not in similar_letters:
			similar_letters.append(character)
			k += 1

	return k
