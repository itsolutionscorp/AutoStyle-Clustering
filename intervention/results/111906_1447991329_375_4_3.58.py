def num_common_letters(*strings):
    x =set.intersection(*map(set,strings))
    return len(x)


Positive Hints
...using a call to set....using a binary operation (<<, >>, &, ^, ~, |). Try to combine it with your call to set..

Negative Hints
...not using a call to map.

Approach Hints


Skeleton
