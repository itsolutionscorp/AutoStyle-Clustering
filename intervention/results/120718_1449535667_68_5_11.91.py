def num_common_letters(goal_word, guess):
	# add your code here
	count = 0
	l = []	
	for g in guess:
		if g in set(goal_word) and g not in l:
			l += g
			count += 1
	return count
	# restrict your code to a single function


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using a call to len....using set comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
