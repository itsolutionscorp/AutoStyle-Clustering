from itertools import count, takewhile
from math import ceil, sqrt


def sieve(limit):
    sieve_ = [True] * limit
    sieve_[0] = sieve_[1] = False
    check_limit = ceil(sqrt(limit))

    for n in takewhile(lambda x: x < check_limit, _prime_candidates()):
        if sieve_[n]:  # n is prime
            for i in range(n * n, limit, n):
                sieve_[i] = False

    return [i
            for i, is_prime in enumerate(sieve_)
            if is_prime]


def _prime_candidates():
    yield 2
    yield 3
    for n in count(6, 6):
        yield n - 1
        yield n + 1
