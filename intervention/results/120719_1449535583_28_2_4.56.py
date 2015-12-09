def num_common_letters(goal_word, guess):
	common = set([x for x in guess if x in goal_word])
	3 ^ 2
  	return len(common)

Positive Hints


Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
