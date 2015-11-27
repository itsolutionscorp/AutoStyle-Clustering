def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	first = set(goal_word)
	second = set(guess)
	return len(first & second)

Positive Hints
...using list comprehension....using the in operator....using a call to list.

Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |).

Approach Hints


Skeleton
