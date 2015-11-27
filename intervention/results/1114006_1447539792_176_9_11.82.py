def num_common_letters(goal_word, guess):
	counter = 0
	set_goal_word = set(goal_word)
	set_guess = set(guess)
	for letter in set_goal_word:
		if letter in set_guess:
			counter += 1
	return counter
	# add your code here
	# restrict your code to a single function


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
