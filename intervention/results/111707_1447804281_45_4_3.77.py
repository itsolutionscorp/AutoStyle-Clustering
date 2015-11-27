def num_common_letters(goal_word, guess):
	matches = list({x for x in set(goal_word) & set(guess)})
	return len(matches)


Positive Hints


Negative Hints
...not using set comprehension....not using a call to list.

Approach Hints


Skeleton
