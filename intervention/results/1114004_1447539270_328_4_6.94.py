def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	goal_word = set(goal_word)
	guess = set(guess)
	return len([c for c in goal_word if c in guess])

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
