def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	return len([letter for letter in set(guess) if letter in goal_word])


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
