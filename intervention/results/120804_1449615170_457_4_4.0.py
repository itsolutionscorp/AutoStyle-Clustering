def num_common_letters(goal_word, guess):
    goal, check = set(goal_word), set(guess)
    return len(list(goal.intersection(check)))


Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to list.

Approach Hints


Skeleton
