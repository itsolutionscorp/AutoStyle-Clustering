def num_common_letters(goal_word, guess):
	return len([letter for letter in list(set(goal_word)) if letter in list(set(guess))])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
