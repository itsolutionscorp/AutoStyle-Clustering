def num_common_letters(goal_word, guess):
	i = 0
	used = []
	for char in guess:
		if char not in used and char in goal_word:
			i+=1
			used.append(char)
	return i

Positive Hints
...using a call to len....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
