def num_common_letters(goal_word, guess):
    count1 = 0
    count2 = 0
    for elem_goal in goal_word:
    	for elem_guess in guess:
    		if elem_guess == elem_goal:
    			count1 += 1
    			break
    for elem_guess in guess:
    	for elem_goal in goal_word:
    		if elem_goal == elem_guess:
    			count2 += 1
    			break
    return min(count1, count2)
