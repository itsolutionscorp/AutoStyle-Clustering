def num_common_letters(goal_word, guess):
	match_list = []
	
	for x in goal_word:
		if x in guess:
			if x not in match_list:
				match_list.append(x)
	return len(match_list)

Positive Hints
...using the augassign operator....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use nested conditionals (i.e. an if statement inside an if statement)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
