def num_common_letters(goal_word, guess):
	# add your code here
	return len([s for s in set(goal_word) if s in guess]) - (1&0)
	# restrict your code to a single function


Positive Hints


Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |)....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
