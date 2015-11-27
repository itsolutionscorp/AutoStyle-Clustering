def num_common_letters(goal_word, guess):
	total_common = 0
	word_count = []
	for each_letter in guess:
		if each_letter in goal_word and each_letter not in word_count:
			word_count.append(each_letter)
			total_common += 1
	return total_common

Positive Hints
...using a call to len....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
