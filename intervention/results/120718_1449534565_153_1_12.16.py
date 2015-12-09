def num_common_letters(goal_word, guess):
	s, l = set(), []	
	count = 0
	for i in range(len(goal_word)):
		s.add(goal_word[i])
	for i in range(len(guess)):
		if guess[i] in s and guess[i] not in l:
			count += 1
			l.append(guess[i])
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
