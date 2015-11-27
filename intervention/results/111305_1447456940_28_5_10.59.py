def num_common_letters(goal_word, guess):
	common_letters = []
	num_common = 0
	for guess_letter in guess:
		for goal_letter in goal_word:
			if (guess_letter == goal_letter) & (guess_letter not in common_letters):
				num_common += 1
				common_letters.append(guess_letter)
	return num_common

Positive Hints
...using the in operator....using a call to len....using set comprehension.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a binary operation (<<, >>, &, ^, ~, |).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
