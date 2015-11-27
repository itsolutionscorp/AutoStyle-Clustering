def num_common_letters(goal_word, guess):
	counter = 0 
	goal_word = list(set(goal_word))
	for letter in goal_word:
		if letter in guess:
			counter += 1
	return counter 

	# add your code here
	# restrict your code to a single function


Positive Hints
...using a call to len....using a call to range....using list comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to set....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
