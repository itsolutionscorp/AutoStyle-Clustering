def num_common_letters(goal_word, guess):
	count = 0
	goal_word = set(list(goal_word))
	guess = set(list(guess))
	union = [x for x in guess if x in goal_word]
	return len(union)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using a call to list....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
