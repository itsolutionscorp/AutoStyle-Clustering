def num_common_letters(goal_word, guess):
	word_count = []
	for word in guess:
		if word not in word_count:
			word_count.append(word)

	total_common = 0
	letter_count = []
	for letter in goal_word:
		if letter in word_count and letter not in letter_count:
			letter_count.append(letter)
			total_common += 1
	return total_common

Positive Hints
...restructuring your function to use nested conditionals....restructuring your function to use nested loops....using a call to len.

Negative Hints
...restructuring your function to not use sequential iteration blocks (loops)....restructuring your program to eliminate redundant code.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
