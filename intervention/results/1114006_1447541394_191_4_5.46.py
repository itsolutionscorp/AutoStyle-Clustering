def num_common_letters(goal_word, guess):
	lst = [x for x in goal_word if x in guess]
	return len(set(lst))
	# add your code here
	# restrict your code to a single function


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
