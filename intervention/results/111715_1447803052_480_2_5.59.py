def num_common_letters(goal_word, guess):
    a = set(list(goal_word))
    b = set(list(guess))
    return len(a.intersection(b))

Positive Hints
...using list comprehension....using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using a call to list.

Approach Hints
Think about what python construct/function you can use to get all the unique letters in each word. Then consider how you can compare these using a single operation. (HINT: use the suggestions above).

Skeleton
