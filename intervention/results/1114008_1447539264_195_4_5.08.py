def num_common_letters(goal_word, guess):
	goal_word = set(goal_word)
	guess = set(guess)
	return len(guess & goal_word)

Positive Hints
...using list comprehension....using the in operator....using a call to list.

Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |).

Approach Hints


Skeleton
