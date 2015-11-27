def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	count = 0
	temp = []
	for char in list(goal_word):
		if char in list(guess) and char not in temp:
			count += 1
			temp += char
	return count

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |)....using a call to len....using set comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list....not using the augassign operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
