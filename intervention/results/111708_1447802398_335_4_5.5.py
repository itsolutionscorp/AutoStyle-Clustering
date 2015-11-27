def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	goal_word, guess = set([letter for letter in goal_word]), set([letter for letter in guess])
	return len(set.intersection(guess, goal_word))

Positive Hints
...using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension.

Approach Hints


Skeleton
