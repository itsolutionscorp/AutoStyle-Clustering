def num_common_letters(goal_word, guess):
	i = 0
	c = 0
	count = 0
	match = []
	letters = []
	for i in range(0, len(goal_word)):
		letters.append(goal_word[i])
		i += 1
	for c in range(0, len(guess)):
		if guess[c] in letters and guess[c] not in match:
			match.append(guess[c])
			c += 1
	return len(match)


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
