def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	lst = []
	lettersum = 0
	for i in range(len(goal_word)):
		if not goal_word[i] in lst:
			lst.append(goal_word[i])
	for j in range(len(guess)):
		if guess[j] in lst:
			lettersum += 1
			lst.remove(guess[j])
	return lettersum



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
