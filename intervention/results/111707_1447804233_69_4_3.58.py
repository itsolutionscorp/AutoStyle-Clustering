def num_common_letters(goal_word, guess):
	matches = {x for x in set(goal_word) & set(guess)}
	return len(matches)

Positive Hints
...using a call to list.

Negative Hints
...not using set comprehension.

Approach Hints


Skeleton
