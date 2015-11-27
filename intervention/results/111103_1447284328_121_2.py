def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	commonLetters = []
	for letter in list(guess):
		if letter in list(goal_word):
			if letter not in commonLetters:
				commonLetters.append(letter)
	return len(commonLetters)

Positive Hints
...using the augassign operator....using list comprehension....using a call to set....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use nested conditionals....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
