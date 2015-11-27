def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	unique_goal = list(set(goal_word))
	unique_guess = list(set(guess))
	return len([char for char in unique_guess if char in unique_goal])


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
