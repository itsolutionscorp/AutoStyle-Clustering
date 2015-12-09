def num_common_letters(goal_word, guess):
	elements = set(letter for letter in goal_word if letter in guess)
	return len(elements)

Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set.....using a call to list.

Negative Hints
...not using a generator expression....not using the in operator.

Approach Hints


Skeleton
