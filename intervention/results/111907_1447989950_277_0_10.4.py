def num_common_letters(goal_word, guess):
	i = 0
	match = []
	for x in list(goal_word):
		if guess[i] in list(goal_word) and guess[i] not in match:
			match += guess[i]
			i += 1
	return len(match)


Positive Hints
...using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
