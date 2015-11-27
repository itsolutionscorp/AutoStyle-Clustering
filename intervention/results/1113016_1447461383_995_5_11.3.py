def num_common_letters(goal_word, guess):
	# add your code here
	# restrict your code to a single function
    found = []
    count = 0
    for i in guess:
        if (i in goal_word and i not in found):
            found.append(i)
            count +=1
    return count

Positive Hints
...using a call to len....using a call to set....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension).

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
