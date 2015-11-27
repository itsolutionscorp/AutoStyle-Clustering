def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	s = set(goal_word)
	a = set(guess)
	u = s & a
	return len(u)

Positive Hints
...using list comprehension....using the in operator.

Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
