def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
    lst = []
    return len([lst.append(x) for x in goal_word if (x in guess and x not in lst)])


Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
