def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	count = 0
	chars = []
	for c in goal_word:
		if c in guess and c not in chars:
			count += 1
			chars.append(c)
	return count
	


Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
