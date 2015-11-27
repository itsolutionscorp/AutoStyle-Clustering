def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	return len(set([letter for letter in guess if letter in goal_word]))


Positive Hints
...using a call to list....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
