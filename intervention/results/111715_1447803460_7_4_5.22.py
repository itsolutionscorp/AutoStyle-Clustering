def num_common_letters(goal_word, guess):
    a = set(goal_word)
    b = set(guess)
    return len(a.intersection(b))

Positive Hints
...using list comprehension....using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set.....using a call to list.

Negative Hints


Approach Hints


Skeleton
