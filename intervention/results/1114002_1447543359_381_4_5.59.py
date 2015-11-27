def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	unique = list(set(goal_word))
	return len([match for match in unique if match in guess])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to list....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
