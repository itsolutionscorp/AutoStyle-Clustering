def num_common_letters(goal_word, guess):
	count, gw_temp, l_guess = 0, [], list(guess)
	for char in goal_word:
		if char not in gw_temp:
			gw_temp.append(char)
	for char in gw_temp:
		if char in l_guess:
			l_guess.remove(char)
			count += 1
	return count

Positive Hints


Negative Hints


Approach Hints
Consider this approach: for each letter in the first word, think about how can you find whether it is also in the second word. To find whether it is also in the second word, you should only have to use a single line of code. Use the skeleton we've provided if you're stuck.

Skeleton
def num_common_letters(---------, -----):
    --------- = -
    --------- = []
    for - in list(---------):
        if - in list(-----):
            --------- += - 
            --------- += -
    return ---------    
