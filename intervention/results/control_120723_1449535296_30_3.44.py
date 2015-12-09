def num_common_letters(goal_word, guess):
	
    return len([i for i in set(goal_word) if i in guess])
