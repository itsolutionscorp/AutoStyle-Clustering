def num_common_letters(goal_word, guess):
    return len([i for i in set(goal_word) if i in set(guess)])


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with you call to set..

Negative Hints
...not using list comprehension....not using the in operator.

Approach Hints


Skeleton
