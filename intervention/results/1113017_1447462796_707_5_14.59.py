def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	goal_matrix = list(set(list(goal_word)))

	guess_matrix = list(set(list(guess)))
	counter = 0
	while guess_matrix:
		if guess_matrix[0] in goal_matrix:
			counter += 1
		guess_matrix = guess_matrix[1:len(guess_matrix)]
	return counter


Positive Hints
...using a call to range....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to set....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
