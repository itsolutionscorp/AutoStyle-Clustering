def num_common_letters(goal_word, guess):
	return len(set(list(goal_word)).intersection(list(guess)))

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to list.

Approach Hints


Skeleton
