def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	goal_matrix = []
	while goal_word:
		if not goal_word[0:1] in goal_matrix:
			goal_matrix.append(goal_word[0:1])
		goal_word = goal_word[1:len(goal_word)]

	return_value = 0
	guess_matrix = []

	while guess:
		if not guess[0:1] in guess_matrix:
			guess_matrix.append(guess[0:1])
		guess = guess[1:len(guess)]

	while guess_matrix:
		if guess_matrix[0] in goal_matrix:
			print(guess_matrix[0])
			return_value += 1
		guess_matrix = guess_matrix[1:len(guess_matrix)]

	return return_value

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
