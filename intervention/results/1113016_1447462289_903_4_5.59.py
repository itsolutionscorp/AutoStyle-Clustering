def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
    guess = "".join(set(guess))
    return sum([1 if x in goal_word else 0 for x in guess])


Positive Hints
...using a call to len....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to sum....not using an if-expression....not using list comprehension.

Approach Hints


Skeleton
