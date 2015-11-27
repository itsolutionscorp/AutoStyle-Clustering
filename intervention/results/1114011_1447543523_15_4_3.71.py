def num_common_letters(goal_word, guess):
    without_repeats = set(guess)
    return sum([1 for letter in without_repeats if letter in goal_word])

Positive Hints
...using a call to len....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using a call to sum....not using list comprehension....not using the in operator.

Approach Hints


Skeleton
