def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	a = set([letter for letter in goal_word if letter in guess])
	return len(a & a)

Positive Hints
...using a call to list.

Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |)....not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
