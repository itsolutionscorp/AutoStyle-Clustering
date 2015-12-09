def num_common_letters(goal_word, guess):
    intersection = set([letter for letter in goal_word if letter in guess])
    return len(intersection)


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set.....using a call to list.

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
