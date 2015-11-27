def num_common_letters(goal_word, guess):
	total = 0
	cached = []
	for first in goal_word:
		for second in guess:
			if (first == second) and not (second in cached):
				total += 1
				cached += [first]
				
	return total

Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
