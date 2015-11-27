def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	letters = []
	while goal_word:
		for i in range(len(guess)):
			if goal_word[0] == guess[i] and guess[i] not in letters:
				letters += guess[i]
		goal_word = goal_word[1:]
	return len(letters)

Positive Hints
...using the in operator....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
