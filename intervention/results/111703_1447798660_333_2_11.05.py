def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function

	first = set(goal_word)
	second = set(guess)

	commons = [x for x in first if x in second]
	return len(commons)
	

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
