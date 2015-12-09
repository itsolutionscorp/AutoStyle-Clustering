def num_common_letters(goal_word, guess):
	seen = []
	updated_seen = [seen.append(letter) for letter in guess if letter in goal_word and letter not in seen]
	return len(seen)