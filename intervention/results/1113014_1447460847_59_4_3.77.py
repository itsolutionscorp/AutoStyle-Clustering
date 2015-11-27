def num_common_letters(goal_word, guess):
    if guess != "":
        return len((set(goal_word)).intersection(set(guess)))

Positive Hints
...using a binary operation (<<, >>, &, ^, ~, |).

Negative Hints
...restructuring your function to not use a conditional.

Approach Hints


Skeleton
