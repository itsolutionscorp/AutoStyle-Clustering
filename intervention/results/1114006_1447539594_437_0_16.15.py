def num_common_letters(goal_word, guess):
	counter = 0
	cache = []
	set_goal_word = set(goal_word)
	set_guess = set(guess)
	for letter in set_goal_word:
		for x in set_guess: 
			if letter == x and letter not in cache:
				cache.append(letter)
				counter += 1
	return counter

	# add your code here
	# restrict your code to a single function


Positive Hints
...using a call to len....using the in operator.

Negative Hints
...restructuring your function to not use nested loops (i.e. a loop within a loop)....restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to set.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
