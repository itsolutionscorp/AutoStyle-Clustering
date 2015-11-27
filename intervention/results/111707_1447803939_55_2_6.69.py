def num_common_letters(goal_word, guess):
	goal_set = set(goal_word)
	guess_set = set(guess)
	matches = goal_set & guess_set
	return len(matches)

Positive Hints
...using list comprehension....using the in operator....using a call to list.

Negative Hints
...not using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
