def num_common_letters(a, b):
	count = 0
	letters = []
	for _ in list(a):
		if _ in list(b) and _ not in letters:
			letters.append(_)
			count += 1
	return count

Positive Hints
...using a call to len....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
