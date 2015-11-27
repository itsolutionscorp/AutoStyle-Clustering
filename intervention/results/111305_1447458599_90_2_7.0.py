def num_common_letters(goal_word, guess):
	letters = []
	for letter in set(goal_word):
		if letter in set(guess):
			letters.append(letter)
	return len(letters)

Positive Hints
...using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to set.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
