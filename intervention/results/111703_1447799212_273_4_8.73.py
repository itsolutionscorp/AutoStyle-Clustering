def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function

	first = set(goal_word)
	second = set(guess)

	return len([x for x in first if x in second])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
