def num_common_letters(goal_word, guess):
	return len([letter for letter in set(goal_word) if letter in set(guess)])


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
