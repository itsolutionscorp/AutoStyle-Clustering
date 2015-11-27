def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
    return len({x:0 for x in goal_word if x in guess})

Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |)....using a call to list.

Negative Hints
...not using dictionary comprehension....not using the in operator.

Approach Hints


Skeleton
