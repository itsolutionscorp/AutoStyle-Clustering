def num_common_letters(goal_word, guess):
	l = set([g for g in guess if g in set(goal_word)])
	# for g in guess:
	# 	if g in set(goal_word) and g not in l:
	# 		l += g
	return len(l)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using a call to list.

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
