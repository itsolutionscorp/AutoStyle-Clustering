def num_common_letter(goal_word, guess):
	already_viewed, count = [], 0;
	for w1 in guess:
		for w2 in goal_work:
			if w1 == w2:
				if w2 in already_viewed:
					break
				already_viewed.append(w1)					
				count += 1
	return count

Positive Hints
...using a call to len....using a call to set....using list comprehension.

Negative Hints
...restructuring your function to not use nested conditionals (i.e. an if statement inside an if statement)....restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
