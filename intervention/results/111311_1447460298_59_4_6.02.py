def num_common_letters(goal_word, guess):

	unique_word = set(goal_word)
	unique_guess = set(guess)
	return len(unique_word.intersection(unique_guess))

Positive Hints
...using list comprehension....using the in operator....using a binary operation (<<, >>, &, ^, ~, |)....using a call to list.

Negative Hints


Approach Hints


Skeleton
