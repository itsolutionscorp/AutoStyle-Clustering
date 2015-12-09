def num_common_letters(goal_word, guess):
	seen = []
	new_seen = [seen.append(x) for x in guess if x in goal_word and x not in seen]
	return len(seen)
	