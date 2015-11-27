def num_common_letters(goal_word, guess):

	matching_letters = []
	total = 0

	for letter in guess:
		if letter in goal_word and letter not in matching_letters:
			matching_letters.append(letter)
			total += 1
	return total

Positive Hints
...using a call to len....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
