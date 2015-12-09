def num_common_letters2(goal_word, guess):
    goal, check = set(goal_word), set(guess)
    return len(goal.intersection(check))

Positive Hints
...using list comprehension....using the in operator....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints


Approach Hints


Skeleton
