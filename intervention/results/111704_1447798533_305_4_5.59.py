def num_common_letters(goal_word, guess):
	# add your code here
	guess = list(set(guess))
	return len([letter for letter in guess if letter in goal_word]) 
	# restrict your code to a single function


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using a call to list....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
