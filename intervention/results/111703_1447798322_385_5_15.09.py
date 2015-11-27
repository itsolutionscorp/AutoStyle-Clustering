def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function

	
	count = 0
	commons = []
	for letter1 in goal_word:
		for letter2 in guess:
			if letter1 == letter2 and not letter1 in commons:
				count += 1
				commons.append(letter1)

	return count

Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using the in operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
