def num_common_letters(goal_word, guess):
	# add your code here
	return len([s for s in set(goal_word) if s in guess])
	# restrict your code to a single function


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
