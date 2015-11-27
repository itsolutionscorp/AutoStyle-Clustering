def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	a = [letter for letter in goal_word if letter in guess]
	return len(set(a))

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
