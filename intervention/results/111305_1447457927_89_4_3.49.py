def num_common_letters(goal_word, guess):
	return sum([letter in list(set(guess)) for letter in list(set(goal_word))])

Positive Hints
...using a call to len....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list....not using a call to sum....not using list comprehension.

Approach Hints


Skeleton
