def num_common_letters(goal_word, guess):
	# add your code here
	similar = []
	for letter in guess:
		if letter in goal_word and not letter in similar:
			similar.append(letter)
	return len(similar)
	# restrict your code to a single function

Positive Hints
...using the augassign operator....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
