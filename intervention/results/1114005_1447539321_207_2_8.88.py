def num_common_letters(goal_word, guess):
	# add your code here
	same = 0
	for s in set(goal_word):
		if s in guess:
			same += 1
	return same
	# restrict your code to a single function


Positive Hints
...using a call to len....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to set.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
