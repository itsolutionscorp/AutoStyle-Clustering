def num_common_letters(goal_word, guess):
	elements = set()
	for letter in guess:
		if letter in goal_word and letter not in elements:
			elements.update({letter})
	return len(elements)

Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set.....using a call to list.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
