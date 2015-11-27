def num_common_letters(goal_word, guess):
	number = 0
	if guess in goal_word:
		number += 1
	while guess in goal_word:
	    goal_word = goal_word.replace(guess, "")

	for i in goal_word:
		for j in guess:
			if i == j:
				number += 1
	return number

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
