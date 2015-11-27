def num_common_letters(goal_word, guess):
	goal_word = ''.join(sorted(''.join(set(goal_word))))
	guess = ''.join(sorted(''.join(set(guess))))
	print(goal_word, guess)
	count = 0
	index1, index2 = 0, 0
	while index1 < len(goal_word) and index2 < len(guess):
		if goal_word[index1] < guess[index2]:
			index1 += 1
		elif goal_word[index2] > guess[index2]:
			index2 += 1
		else:
			count += 1
			index1 += 1
			index2 += 1
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
        if - in list(-----) --- - --- in ---------:
            --------- += - 
            --------- += -
    return ---------    
