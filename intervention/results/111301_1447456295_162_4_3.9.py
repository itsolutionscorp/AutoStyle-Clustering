def num_common_letters(goal_word, guess):
	common = [l for l in set(goal_word) if l in set(guess)]
	return len(common)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
