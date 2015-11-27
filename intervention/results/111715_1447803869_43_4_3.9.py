def num_common_letters(goal_word, guess):
    a = set([letter for letter in goal_word if letter in list(guess)])
    return len(a)

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using a call to list....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
