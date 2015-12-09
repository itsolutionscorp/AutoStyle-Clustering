def num_common_letters(goal_word, guess):
	common = [x for x in set(guess) if x in goal_word]
  	return len(common)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
