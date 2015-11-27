def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
	str1 = list(goal_word)
	str2 = list(guess)
	num_letters_set = set(str1).intersection(str2)
	return len(num_letters_set)


Positive Hints
...using list comprehension....using the in operator....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
