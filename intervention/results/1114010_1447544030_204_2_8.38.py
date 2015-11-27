def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	s = set([i for i in goal_word])
	a = set([j for j in guess])
	u = s.intersection(a)
	return len(u)

Positive Hints
...using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using list comprehension.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
