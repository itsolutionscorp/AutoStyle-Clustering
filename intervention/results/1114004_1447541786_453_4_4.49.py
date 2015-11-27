def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	return len([c for c in set(goal_word) if c in set(guess)])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
