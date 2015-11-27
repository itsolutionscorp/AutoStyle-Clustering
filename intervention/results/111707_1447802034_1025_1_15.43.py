def num_common_letters(goal_word, guess):
	goal_word_list = []
	matches = []
	count = 0 
	for x in range(0, len(goal_word)):
		goal_word_list += [goal_word[x]]
	for x in range(0, len(guess)):
		if guess[x] in goal_word_list and guess[x] not in matches:
			count += 1
			matches += [guess[x]]
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
