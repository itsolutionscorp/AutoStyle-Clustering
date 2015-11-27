def num_common_letters(goal_word, guess):
    total = 0
    for i in list(set(goal_word)):
        if i in list(set(guess)):
            total += 1
    return total

Positive Hints
...using a call to len....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list....not using the augassign operator.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
