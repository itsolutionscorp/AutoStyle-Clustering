def num_common_letters(goal_word, guess):
  	union = [x for x in guess if x in goal_word]
  	return len(set(union))

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
