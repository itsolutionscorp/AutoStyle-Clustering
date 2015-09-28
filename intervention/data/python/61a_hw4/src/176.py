def num_common_letters(goal_word, guess):
    goal_word_str, guess_str = get_string(goal_word), get_string(guess)
    count, same_letter = 0, ''
    for ltr in goal_word_str:
    	for char in guess_str:
    		if char == ltr and char != same_letter:
    			count += 1
    			same_letter = ltr
    	same_letter = ''
    return count
