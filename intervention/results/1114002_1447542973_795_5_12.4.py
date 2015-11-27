def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	repeat = []
	total = 0
	for i in goal_word:
		if i not in repeat:
	 		if i in guess:
	 			total += 1
	 			repeat.append(i)
	return total 

Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use nested conditionals (i.e. an if statement inside an if statement)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
