def num_common_letters(goal_word, guess):
    x = [letter for letter in goal_word]
    y= [letter for letter in guess]
    a = set(x)
    b = set(y)
    return len(a.intersection(b))

Positive Hints
...using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension.

Approach Hints


Skeleton
