def num_common_letters(goal_word, guess):
	count = 0
	goal_word = set(list(goal_word))
	guess = set(list(guess))
	for x in goal_word:
		if x in guess:
			count += 1
	return count

Positive Hints
...using a call to len....using a call to range....using list comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
