def num_common_letters(goal_word, guess):
	# add your code here
	l = list()
	for g in guess:
		if g in set(goal_word) and g not in l:
			l += g
	return len(l)
	# restrict your code to a single function


Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
