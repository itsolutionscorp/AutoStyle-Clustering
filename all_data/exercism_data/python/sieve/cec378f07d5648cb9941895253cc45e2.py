from __future__ import division
from math import sqrt, ceil


def sieve(limit):
    candidates = [True for i in range(limit - 1)]

    for i in range(2, int(ceil(sqrt(limit)))):
        if not candidates[i - 2]:
            continue
        for j in range(i, limit // i + 1):
            candidates[i * j - 2] = False

    return [i + 2 for i in range(len(candidates)) if candidates[i]]
