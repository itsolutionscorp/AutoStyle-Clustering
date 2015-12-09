def num_common_letters(goal_word, guess):
	elements = set(list(letter for letter in goal_word if letter in guess))
	return len(elements)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to list....not using a generator expression....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
