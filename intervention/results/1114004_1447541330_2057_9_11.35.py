def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	gw, g = set(goal_word), set(guess)
	n = 0
	for c1 in gw:
		for c2 in g:
			if c1 == c2:
				n += 1	
	return n


Positive Hints


Negative Hints


Approach Hints
Consider a different approach to solving this problem. Try iterating over every letter in the first word. For each letter in the first word, think about how can you find whether it is also in the second word. To find whether it is also in the second word, you should only have to use a single line of code. Use the skeleton we've provided if you're stuck.

Skeleton
def num_common_letters(---------, -----):
    --------- = -
    --------- = []
    for - in list(---------):
        if - in list(-----) --- - --- in ---------:
            --------- += - 
            --------- += -
    return ---------    
