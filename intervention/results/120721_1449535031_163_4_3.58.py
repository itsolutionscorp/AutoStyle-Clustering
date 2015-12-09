def num_common_letters(goal_word, guess):
	elements = {letter for letter in goal_word if letter in guess}
	return len(elements)

Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using the in operator....not using set comprehension.

Approach Hints


Skeleton
