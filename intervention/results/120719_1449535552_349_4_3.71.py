def num_common_letters(goal_word, guess):
	common = set([x for x in guess if x in goal_word])
  	return len(common)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using a call to list.

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
