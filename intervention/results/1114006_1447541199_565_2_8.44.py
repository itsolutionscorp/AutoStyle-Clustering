def num_common_letters(goal_word, guess):
	goal_word = set(goal_word)
	guess = set(guess)
	length = {x for x in goal_word if x in guess}
	return len(length)
	# add your code here
	# restrict your code to a single function


Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using set comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
