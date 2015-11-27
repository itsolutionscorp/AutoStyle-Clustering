def num_common_letters(goal_word, guess):
	counter = 0
	cache = []
	for letter in goal_word:
		for x in guess: 
			if letter == x and letter not in cache:
				cache.append(letter)
				counter += 1
	return counter

Positive Hints
...using the in operator....using a call to len....using a call to set.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
