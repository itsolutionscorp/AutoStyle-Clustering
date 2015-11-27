def num_common_letters(goal_word, guess):
	return len(list({x for x in set(goal_word) & set(guess)}))

Positive Hints


Negative Hints
...not using a call to list....not using set comprehension.

Approach Hints


Skeleton
