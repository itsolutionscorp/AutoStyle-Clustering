def num_common_letters(goal_word, guess):
	goal_word = set(goal_word)
	elements = {letter for letter in goal_word if letter in guess}
	return len(elements)

Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set.....using a call to list.

Negative Hints
...not using set comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
