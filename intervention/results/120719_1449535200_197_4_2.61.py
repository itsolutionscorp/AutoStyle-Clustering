def num_common_letters(goal_word, guess):
  	return len(set([x for x in guess if x in goal_word]))

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
