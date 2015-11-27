def num_common_letters(goal_word, guess):
	counter=0
	if guess in goal_word:
		return 1
	for j in range (0,len(goal_word)):
		for i in range (0,len(guess)):
			if guess[i] == goal_word[j]:
				counter+=1
	return counter

Positive Hints
...using list comprehension....using a call to set....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
