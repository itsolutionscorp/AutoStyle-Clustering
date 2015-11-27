def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	repeated = {character for character in goal_word if character in guess}
	return len(repeated)

Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using the in operator....not using set comprehension.

Approach Hints


Skeleton
