def num_common_letters(goal_word, guess):

	unique_word = set(goal_word)
	unique_guess = set(guess)

	return len([x for x in unique_guess if x in unique_word])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
