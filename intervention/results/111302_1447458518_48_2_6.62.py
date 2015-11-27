def num_common_letters(goal_word, guess):
	length = len([l for l in goal_word if (l in guess)])
	if length == 5:
		return length
	else:
		return length - 1

Positive Hints
...using a call to set.

Negative Hints
...restructuring your function to not use a conditional....not using a binary operation (<<, >>, &, ^, ~, |)....not using list comprehension.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
