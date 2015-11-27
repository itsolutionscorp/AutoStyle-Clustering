def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	letters = [letter for letter in goal_word]
	common_letters = 0
	for i in range(len(guess)):
		if guess[i] in letters:
			common_letters += 1
	if guess in goal_word:
		return 1
	return common_letters

Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using list comprehension....not using a call to len.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
