def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	s = set([goal_word[i] for i in range(len(goal_word))])
	a = set([guess[j] for j in range(len(guess))])
	u = s.intersection(a)
	return len(u)

Positive Hints
...using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to range....not using list comprehension.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
