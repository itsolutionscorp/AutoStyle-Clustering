def num_common_letters(goal_word, guess):
    letters = list(set(goal_word))
    repeats = 0
    for l in list(set(guess)):
        if l in letters:
            repeats += 1
    return repeats

Positive Hints
...using a call to len....using list comprehension....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...restructuring your function to not use a conditional....restructuring your function to not use an explicit loop (e.g. use list/dict comprehension)....not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
