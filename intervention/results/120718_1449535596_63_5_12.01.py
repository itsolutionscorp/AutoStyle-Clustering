def num_common_letters(goal_word, guess):
	# add your code here
	count = 0
	l = []	
	for i in range(len(guess)):
		if guess[i] in set(goal_word) and guess[i] not in l:
			l += guess[i]
			count += 1
	return count
	# restrict your code to a single function


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using set comprehension.

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to len.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
