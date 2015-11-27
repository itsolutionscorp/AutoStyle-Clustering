def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
    guess = "".join(set(guess))
    return sum(1 if x in goal_word else 0 for x in guess)

Positive Hints
...using a call to len....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...not using a call to set....not using a call to sum....not using an if-expression....not using a generator expression.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
