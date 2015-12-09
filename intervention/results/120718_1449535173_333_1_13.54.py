def num_common_letters(goal_word, guess):
	# add your code here
	s = set(goal_word)
	l = []	
	count = 0	
	for i in range(len(guess)):
		if guess[i] in s and guess[i] not in l:
			count += 1
			l += guess[i]
	return count
	# restrict your code to a single function


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
