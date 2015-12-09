def num_common_letters(goal_word, guess):
	count = 0
	elements = set()
	for letter in guess:
		if letter in goal_word and letter not in elements:
			elements.update({letter})
			count += 1
	return count

Positive Hints
...using a call to len....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
